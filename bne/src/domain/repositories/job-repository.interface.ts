import type { JobOffer, JobSearchCriteria } from "../entities/job-offer"

export interface JobRepository {
  searchJobs(criteria: JobSearchCriteria): Promise<{
    jobs: JobOffer[]
    totalCount: number
    currentPage: number
    totalPages: number
  }>

  getJobById(id: string): Promise<JobOffer | null>
}
