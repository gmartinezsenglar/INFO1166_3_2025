"use client"

import { Dialog, DialogContent, DialogHeader, DialogTitle } from "@/components/ui/dialog"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { Separator } from "@/components/ui/separator"
import type { JobOffer } from "../../domain/entities/job-offer"

interface JobDetailModalProps {
  job: JobOffer
  onClose: () => void
}

export function JobDetailModal({ job, onClose }: JobDetailModalProps) {
  const formatDate = (date: Date) => {
    return date.toLocaleDateString("es-CL", {
      day: "2-digit",
      month: "2-digit",
      year: "numeric",
    })
  }

  return (
    <Dialog open={true} onOpenChange={onClose}>
      <DialogContent className="max-w-2xl max-h-[80vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle className="text-xl text-primary">{job.title}</DialogTitle>
        </DialogHeader>

        <div className="space-y-6">
          {/* Job basic info */}
          <div className="grid grid-cols-2 gap-4">
            <div>
              <h3 className="font-semibold mb-2">Empresa:</h3>
              <p>{job.company}</p>
            </div>
            <div>
              <h3 className="font-semibold mb-2">Ubicación:</h3>
              <p>{job.location}</p>
            </div>
          </div>

          {/* Salary if available */}
          {job.salary && (
            <div>
              <h3 className="font-semibold mb-2">Salario:</h3>
              <Badge variant="secondary">{job.salary}</Badge>
            </div>
          )}

          <Separator />

          {/* Job description */}
          <div>
            <h3 className="font-semibold mb-2">Descripción:</h3>
            <p className="text-sm leading-relaxed">{job.description}</p>
          </div>

          {/* Requirements */}
          <div>
            <h3 className="font-semibold mb-2">Requisitos:</h3>
            <p className="text-sm leading-relaxed">{job.requirements}</p>
          </div>

          <Separator />

          {/* Job characteristics */}
          <div className="grid grid-cols-2 gap-4 text-sm">
            <div>
              <h4 className="font-medium mb-1">Tipo de contrato:</h4>
              <p className="text-muted-foreground">{job.contractType}</p>
            </div>
            <div>
              <h4 className="font-medium mb-1">Jornada laboral:</h4>
              <p className="text-muted-foreground">{job.workSchedule}</p>
            </div>
            <div>
              <h4 className="font-medium mb-1">Nivel educativo:</h4>
              <p className="text-muted-foreground">{job.educationLevel}</p>
            </div>
            <div>
              <h4 className="font-medium mb-1">Fecha de publicación:</h4>
              <p className="text-muted-foreground">{formatDate(job.publishedDate)}</p>
            </div>
          </div>

          <Separator />

          {/* Action buttons */}
          <div className="flex gap-4 justify-center">
            <Button size="lg" className="px-8">
              Postular
            </Button>
            <Button variant="outline" size="lg" onClick={onClose}>
              Cerrar
            </Button>
          </div>
        </div>
      </DialogContent>
    </Dialog>
  )
}
