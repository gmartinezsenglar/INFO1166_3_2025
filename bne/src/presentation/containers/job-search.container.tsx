"use client";

import { useState, useEffect } from "react";
import { SearchJobsUseCase } from "../../application/use-cases/search-jobs.use-case";
import { GetJobDetailUseCase } from "../../application/use-cases/get-job-detail.use-case";
import { MockJobRepository } from "../../infrastructure/repositories/mock-job.repository";
import type {
  JobOffer,
  JobSearchCriteria,
} from "../../domain/entities/job-offer";
import { JobSearchView } from "../views/job-search.view";

interface JobSearchContainerProps {
  jobType: "bne" | "external";
}

export function JobSearchContainer({ jobType }: JobSearchContainerProps) {
  const jobRepository = new MockJobRepository();
  const searchJobsUseCase = new SearchJobsUseCase(jobRepository);
  const getJobDetailUseCase = new GetJobDetailUseCase(jobRepository);

  const [jobs, setJobs] = useState<JobOffer[]>([]);
  const [totalCount, setTotalCount] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(false);
  const [selectedJob, setSelectedJob] = useState<JobOffer | null>(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [filters, setFilters] = useState<
    Omit<JobSearchCriteria, "query" | "page" | "pageSize">
  >({
    fechaPublicacion: "",
    region: "",
    comuna: "",
    ocupacion: "",
    grupoEmpleo: "",
    nivelEducativo: "",
    jornadaLaboral: "",
    tipoContrato: "",
    origenOferta: "",
  });

  const searchJobs = async (criteria: JobSearchCriteria) => {
    setLoading(true);
    try {
      const result = await searchJobsUseCase.execute({
        ...criteria,
        query: criteria.query || jobType,
      });
      setJobs(result.jobs);
      setTotalCount(result.totalCount);
      setCurrentPage(result.currentPage);
      setTotalPages(result.totalPages);
    } catch (error) {
      console.error("Error searching jobs:", error);
    } finally {
      setLoading(false);
    }
  };

  const getJobDetail = async (jobId: string) => {
    try {
      const job = await getJobDetailUseCase.execute(jobId);
      setSelectedJob(job);
    } catch (error) {
      console.error("Error getting job detail:", error);
    }
  };

  useEffect(() => {
    searchJobs({ query: jobType, page: 1, pageSize: 10 });
  }, [jobType]);

  const handleSearch = (query: string) => {
    setSearchQuery(query);
    searchJobs({
      query: `${jobType} ${query}`.trim(),
      ...filters,
      page: 1,
      pageSize: 10,
    });
  };

  const handleFiltersChange = (newFilters: typeof filters) => {
    setFilters(newFilters);
    searchJobs({
      query: `${jobType} ${searchQuery}`.trim(),
      ...newFilters,
      page: 1,
      pageSize: 10,
    });
  };

  const handlePageChange = (page: number) => {
    searchJobs({
      query: `${jobType} ${searchQuery}`.trim(),
      ...filters,
      page,
      pageSize: 10,
    });
  };

  return (
    <JobSearchView
      jobType={jobType}
      jobs={jobs}
      totalCount={totalCount}
      currentPage={currentPage}
      totalPages={totalPages}
      loading={loading}
      selectedJob={selectedJob}
      searchQuery={searchQuery}
      filters={filters}
      onSearch={handleSearch}
      onFiltersChange={handleFiltersChange}
      onPageChange={handlePageChange}
      onJobSelect={getJobDetail}
      onJobModalClose={() => setSelectedJob(null)}
    />
  );
}
