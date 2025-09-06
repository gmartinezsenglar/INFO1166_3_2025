"use client"

import { Card, CardContent } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import type { JobOffer } from "../../domain/entities/job-offer"

interface JobListingsProps {
  jobs: JobOffer[]
  totalCount: number
  currentPage: number
  totalPages: number
  loading: boolean
  onJobSelect: (jobId: string) => void
  onPageChange: (page: number) => void
}

export function JobListings({
  jobs,
  totalCount,
  currentPage,
  totalPages,
  loading,
  onJobSelect,
  onPageChange,
}: JobListingsProps) {
  const formatDate = (date: Date) => {
    const now = new Date()
    const diffTime = Math.abs(now.getTime() - date.getTime())
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))

    if (diffDays === 1) return "Hoy"
    if (diffDays === 2) return "Ayer"
    if (diffDays <= 3) return `Hace ${diffDays - 1} días`
    if (diffDays <= 7) return `Hace ${diffDays - 1} días`
    return `Hace ${diffDays - 1} días`
  }

  if (loading) {
    return (
      <div className="space-y-4">
        <div className="flex items-center justify-between">
          <h2 className="text-lg font-semibold">Cargando ofertas...</h2>
        </div>
        <div className="space-y-4">
          {[...Array(5)].map((_, i) => (
            <Card key={i} className="animate-pulse">
              <CardContent className="p-4">
                <div className="h-4 bg-muted rounded w-3/4 mb-2"></div>
                <div className="h-3 bg-muted rounded w-1/2 mb-2"></div>
                <div className="h-3 bg-muted rounded w-full"></div>
              </CardContent>
            </Card>
          ))}
        </div>
      </div>
    )
  }

  return (
    <div className="space-y-4">
      {/* Header with results count and pagination controls */}
      <div className="flex items-center justify-between">
        <h2 className="text-lg font-semibold">{totalCount.toLocaleString()} ofertas de empleo</h2>
        <div className="flex items-center gap-4">
          <div className="flex items-center gap-2">
            <span className="text-sm">Resultados por página:</span>
            <Select defaultValue="10">
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
      </div>

      {/* Job listings */}
      <div className="space-y-4">
        {jobs.map((job) => (
          <Card key={job.id} className="hover:shadow-md transition-shadow">
            <CardContent className="p-4">
              <div className="flex items-start justify-between">
                <div className="flex-1">
                  <div className="flex items-center gap-2 mb-2">
                    <Badge variant="secondary" className="text-xs">
                      {formatDate(job.publishedDate)}
                    </Badge>
                    {job.salary && (
                      <Badge variant="outline" className="text-xs">
                        {job.salary}
                      </Badge>
                    )}
                  </div>

                  <h3 className="font-semibold text-primary hover:underline cursor-pointer mb-1">{job.title}</h3>

                  <p className="text-sm text-muted-foreground mb-2 line-clamp-2">{job.description}</p>

                  <div className="flex items-center gap-4 text-sm text-muted-foreground">
                    <span className="font-medium text-foreground">{job.company}</span>
                    <span>{job.location}</span>
                  </div>
                </div>

                <Button variant="outline" size="sm" onClick={() => onJobSelect(job.id)} className="ml-4">
                  Ver Más
                </Button>
              </div>
            </CardContent>
          </Card>
        ))}
      </div>

      {/* Pagination */}
      {totalPages > 1 && (
        <div className="flex items-center justify-center gap-2 mt-6">
          <Button
            variant="outline"
            size="sm"
            onClick={() => onPageChange(currentPage - 1)}
            disabled={currentPage === 1}
          >
            Anterior
          </Button>

          <div className="flex items-center gap-1">
            {[...Array(Math.min(5, totalPages))].map((_, i) => {
              const pageNum = i + 1
              return (
                <Button
                  key={pageNum}
                  variant={currentPage === pageNum ? "default" : "outline"}
                  size="sm"
                  onClick={() => onPageChange(pageNum)}
                  className="w-8 h-8"
                >
                  {pageNum}
                </Button>
              )
            })}

            {totalPages > 5 && (
              <>
                <span className="px-2">...</span>
                <Button variant="outline" size="sm" onClick={() => onPageChange(totalPages)} className="w-8 h-8">
                  {totalPages}
                </Button>
              </>
            )}
          </div>

          <Button
            variant="outline"
            size="sm"
            onClick={() => onPageChange(currentPage + 1)}
            disabled={currentPage === totalPages}
          >
            Siguiente
          </Button>
        </div>
      )}

      <div className="text-center text-sm text-muted-foreground">
        Página {currentPage} de {totalPages}
      </div>
    </div>
  )
}
