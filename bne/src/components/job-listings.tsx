"use client"

import { useState, useMemo } from "react"
import { Card, CardContent } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { JobDetailModal } from "./job-detail-modal"
import { mockJobs } from "@/lib/mock-data"

interface JobListingsProps {
  searchQuery: string
  filters: any
  activeTab: "bne" | "external"
}

export function JobListings({ searchQuery, filters, activeTab }: JobListingsProps) {
  const [selectedJob, setSelectedJob] = useState<any>(null)
  const [currentPage, setCurrentPage] = useState(1)
  const [resultsPerPage, setResultsPerPage] = useState(10)

  // Filter jobs based on search query, filters, and active tab
  const filteredJobs = useMemo(() => {
    let jobs = mockJobs.filter((job) => job.type === activeTab)

    // Apply search query
    if (searchQuery) {
      jobs = jobs.filter(
        (job) =>
          job.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
          job.company.toLowerCase().includes(searchQuery.toLowerCase()) ||
          job.description.toLowerCase().includes(searchQuery.toLowerCase()),
      )
    }

    // Apply filters
    if (filters.region) {
      jobs = jobs.filter((job) => job.region === filters.region)
    }
    if (filters.jornadaLaboral) {
      jobs = jobs.filter((job) => job.workSchedule === filters.jornadaLaboral)
    }
    if (filters.tipoContrato) {
      jobs = jobs.filter((job) => job.contractType === filters.tipoContrato)
    }

    return jobs
  }, [searchQuery, filters, activeTab])

  // Pagination
  const totalPages = Math.ceil(filteredJobs.length / resultsPerPage)
  const startIndex = (currentPage - 1) * resultsPerPage
  const paginatedJobs = filteredJobs.slice(startIndex, startIndex + resultsPerPage)

  const formatDate = (date: string) => {
    const today = new Date()
    const jobDate = new Date(date)
    const diffTime = Math.abs(today.getTime() - jobDate.getTime())
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))

    if (diffDays === 1) return "Hoy"
    if (diffDays === 2) return "Ayer"
    if (diffDays <= 3) return `Hace ${diffDays - 1} días`
    if (diffDays <= 7) return `Hace ${diffDays - 1} días`
    return `Hace ${diffDays - 1} días`
  }

  return (
    <div>
      {/* Results header */}
      <div className="flex items-center justify-between mb-4">
        <div className="text-sm text-muted-foreground">{filteredJobs.length} ofertas de empleo</div>
        <div className="flex items-center gap-2">
          <span className="text-sm">Resultados por página:</span>
          <Select
            value={resultsPerPage.toString()}
            onValueChange={(value) => {
              setResultsPerPage(Number.parseInt(value))
              setCurrentPage(1)
            }}
          >
            <SelectTrigger className="w-20">
              <SelectValue />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="10">10</SelectItem>
              <SelectItem value="25">25</SelectItem>
              <SelectItem value="50">50</SelectItem>
            </SelectContent>
          </Select>
        </div>
      </div>

      {/* Job listings */}
      <div className="space-y-4">
        {paginatedJobs.map((job) => (
          <Card key={job.id} className="hover:shadow-md transition-shadow">
            <CardContent className="p-4">
              <div className="flex justify-between items-start">
                <div className="flex-1">
                  <div className="flex items-center gap-2 mb-2">
                    <span className="text-xs text-muted-foreground">{formatDate(job.publishedDate)}</span>
                    {job.salary && (
                      <span className="text-sm font-medium text-green-600">${job.salary.toLocaleString()}</span>
                    )}
                  </div>

                  <h3 className="font-semibold text-card-foreground mb-2 hover:text-primary cursor-pointer">
                    {job.title}
                  </h3>

                  <p className="text-sm text-muted-foreground mb-2 line-clamp-2">{job.description}</p>

                  <div className="flex items-center gap-4 text-sm text-muted-foreground">
                    <span className="font-medium text-foreground">{job.company}</span>
                    <span>{job.location}</span>
                  </div>

                  {job.publisher && (
                    <div className="mt-2 text-xs text-muted-foreground">Nombre del publicador: {job.publisher}</div>
                  )}
                </div>

                <Button variant="outline" size="sm" onClick={() => setSelectedJob(job)} className="ml-4">
                  Ver Más
                </Button>
              </div>
            </CardContent>
          </Card>
        ))}
      </div>

      {/* Pagination */}
      {totalPages > 1 && (
        <div className="flex items-center justify-center gap-2 mt-8">
          <Button
            variant="outline"
            size="sm"
            onClick={() => setCurrentPage(Math.max(1, currentPage - 1))}
            disabled={currentPage === 1}
          >
            Anterior
          </Button>

          {Array.from({ length: Math.min(5, totalPages) }, (_, i) => {
            const page = i + 1
            return (
              <Button
                key={page}
                variant={currentPage === page ? "default" : "outline"}
                size="sm"
                onClick={() => setCurrentPage(page)}
              >
                {page}
              </Button>
            )
          })}

          {totalPages > 5 && (
            <>
              <span className="text-muted-foreground">...</span>
              <Button variant="outline" size="sm" onClick={() => setCurrentPage(totalPages)}>
                {totalPages}
              </Button>
            </>
          )}

          <Button
            variant="outline"
            size="sm"
            onClick={() => setCurrentPage(Math.min(totalPages, currentPage + 1))}
            disabled={currentPage === totalPages}
          >
            Siguiente
          </Button>
        </div>
      )}

      {/* Job Detail Modal */}
      {selectedJob && <JobDetailModal job={selectedJob} isOpen={!!selectedJob} onClose={() => setSelectedJob(null)} />}
    </div>
  )
}
