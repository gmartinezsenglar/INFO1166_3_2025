"use client"

import type { JobOffer, JobSearchCriteria } from "../../domain/entities/job-offer"
import { SearchFilters } from "../components/search-filters.component"
import { JobListings } from "../components/job-listings.component"
import { Breadcrumb } from "../components/breadcrumb.component"
import { SearchBar } from "../components/search-bar.component"
import { JobDetailModal } from "../components/job-detail-modal.component"

interface JobSearchViewProps {
  jobType: "bne" | "external"
  jobs: JobOffer[]
  totalCount: number
  currentPage: number
  totalPages: number
  loading: boolean
  selectedJob: JobOffer | null
  searchQuery: string
  filters: Omit<JobSearchCriteria, "query" | "page" | "pageSize">
  onSearch: (query: string) => void
  onFiltersChange: (filters: Omit<JobSearchCriteria, "query" | "page" | "pageSize">) => void
  onPageChange: (page: number) => void
  onJobSelect: (jobId: string) => void
  onJobModalClose: () => void
}

export function JobSearchView({
  jobType,
  jobs,
  totalCount,
  currentPage,
  totalPages,
  loading,
  selectedJob,
  searchQuery,
  filters,
  onSearch,
  onFiltersChange,
  onPageChange,
  onJobSelect,
  onJobModalClose,
}: JobSearchViewProps) {
  const pageTitle = jobType === "bne" ? "Ofertas BNE" : "Ofertas Externas"

  return (
    <div className="min-h-screen bg-background">
      {/* Header */}
      <header className="bg-primary text-primary-foreground py-4">
        <div className="container mx-auto px-4">
          <div className="flex items-center justify-between">
            <h1 className="text-xl font-bold">Bolsa Nacional de Empleo</h1>
            <div className="flex items-center gap-4 text-sm">
              <button className="hover:underline">Ingreso</button>
              <button className="hover:underline">Registro</button>
              <button className="hover:underline">Recuperar contraseña</button>
            </div>
          </div>
        </div>
      </header>

      <div className="container mx-auto px-4 py-6">
        <Breadcrumb currentPage={pageTitle} />

        <div className="mt-6">
          <SearchBar value={searchQuery} onChange={onSearch} />
        </div>

        {/* Navigation between BNE and External offers */}
        <div className="mt-6 flex gap-4">
          <a
            href="/ofertas-bne"
            className={`px-4 py-2 rounded-t-lg border-b-2 ${
              jobType === "bne"
                ? "bg-primary text-primary-foreground border-primary"
                : "bg-muted text-muted-foreground border-transparent hover:bg-muted/80"
            }`}
          >
            Ofertas BNE
          </a>
          <a
            href="/ofertas-externas"
            className={`px-4 py-2 rounded-t-lg border-b-2 ${
              jobType === "external"
                ? "bg-primary text-primary-foreground border-primary"
                : "bg-muted text-muted-foreground border-transparent hover:bg-muted/80"
            }`}
          >
            Ofertas Externas
          </a>
        </div>

        <div className="mt-6 grid grid-cols-1 lg:grid-cols-4 gap-6">
          <div className="lg:col-span-1">
            <SearchFilters filters={filters} onFiltersChange={onFiltersChange} jobType={jobType} />
          </div>

          <div className="lg:col-span-3">
            <JobListings
              jobs={jobs}
              totalCount={totalCount}
              currentPage={currentPage}
              totalPages={totalPages}
              loading={loading}
              onJobSelect={onJobSelect}
              onPageChange={onPageChange}
            />
          </div>
        </div>
      </div>

      {selectedJob && <JobDetailModal job={selectedJob} onClose={onJobModalClose} />}

      {/* Footer */}
      <footer className="bg-muted mt-12 py-8"></footer>
    </div>
  )
}
