"use client"

import { Dialog, DialogContent, DialogHeader, DialogTitle } from "@/components/ui/dialog"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { Calendar, MapPin, Building, DollarSign, Clock, FileText } from "lucide-react"

interface JobDetailModalProps {
  job: any
  isOpen: boolean
  onClose: () => void
}

export function JobDetailModal({ job, isOpen, onClose }: JobDetailModalProps) {
  if (!job) return null

  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent className="max-w-4xl max-h-[90vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle className="text-xl text-card-foreground">{job.title}</DialogTitle>
        </DialogHeader>

        <div className="space-y-6">
          {/* Job Info Header */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 p-4 bg-muted rounded-lg">
            <div className="space-y-2">
              <div className="flex items-center gap-2">
                <Building className="h-4 w-4 text-muted-foreground" />
                <span className="font-medium">Empresa:</span>
                <span>{job.company}</span>
              </div>
              <div className="flex items-center gap-2">
                <MapPin className="h-4 w-4 text-muted-foreground" />
                <span className="font-medium">Ubicación:</span>
                <span>{job.location}</span>
              </div>
              {job.salary && (
                <div className="flex items-center gap-2">
                  <DollarSign className="h-4 w-4 text-muted-foreground" />
                  <span className="font-medium">Salario:</span>
                  <span className="text-green-600 font-medium">${job.salary.toLocaleString()}</span>
                </div>
              )}
            </div>

            <div className="space-y-2">
              <div className="flex items-center gap-2">
                <Clock className="h-4 w-4 text-muted-foreground" />
                <span className="font-medium">Jornada:</span>
                <Badge variant="secondary">{job.workSchedule}</Badge>
              </div>
              <div className="flex items-center gap-2">
                <FileText className="h-4 w-4 text-muted-foreground" />
                <span className="font-medium">Contrato:</span>
                <Badge variant="secondary">{job.contractType}</Badge>
              </div>
              <div className="flex items-center gap-2">
                <Calendar className="h-4 w-4 text-muted-foreground" />
                <span className="font-medium">Publicado:</span>
                <span>{job.publishedDate}</span>
              </div>
            </div>
          </div>

          {/* Job Description */}
          <div>
            <h3 className="font-semibold text-lg mb-3">Descripción</h3>
            <p className="text-muted-foreground leading-relaxed">{job.fullDescription || job.description}</p>
          </div>

          {/* Requirements */}
          <div>
            <h3 className="font-semibold text-lg mb-3">Requisitos Solicitados</h3>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div>
                <span className="font-medium">Nivel educacional:</span>
                <p className="text-muted-foreground">{job.educationLevel || "No especificado"}</p>
              </div>
              <div>
                <span className="font-medium">Experiencia:</span>
                <p className="text-muted-foreground">{job.experience || "No requerida"}</p>
              </div>
              <div>
                <span className="font-medium">Tipo de contrato:</span>
                <p className="text-muted-foreground">{job.contractType}</p>
              </div>
              <div>
                <span className="font-medium">Nivel de Cargo ofrecido:</span>
                <p className="text-muted-foreground">{job.jobLevel || "Ejecutivo"}</p>
              </div>
            </div>
          </div>

          {/* Company Info */}
          <div>
            <h3 className="font-semibold text-lg mb-3">Información de la Empresa</h3>
            <div className="space-y-2">
              <div>
                <span className="font-medium">Actividad económica:</span>
                <p className="text-muted-foreground">{job.economicActivity || "No especificada"}</p>
              </div>
              <div>
                <span className="font-medium">Descripción de la Empresa:</span>
                <p className="text-muted-foreground">{job.companyDescription || "No disponible"}</p>
              </div>
            </div>
          </div>

          {/* Additional Info */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 p-4 bg-muted rounded-lg">
            <div>
              <span className="font-medium">Origen de la Oferta:</span>
              <p className="text-muted-foreground">{job.origin || "WEB"}</p>
            </div>
            <div>
              <span className="font-medium">Oferta de tipo práctica profesional:</span>
              <p className="text-muted-foreground">{job.isPractice ? "Sí" : "No"}</p>
            </div>
            {job.publisher && (
              <div className="md:col-span-2">
                <span className="font-medium">Publicador:</span>
                <p className="text-muted-foreground">{job.publisher}</p>
              </div>
            )}
          </div>

          {/* Action Buttons */}
          <div className="flex gap-3 pt-4 border-t">
            <Button className="flex-1 bg-primary hover:bg-primary/90">Postular</Button>
            <Button variant="outline" onClick={onClose}>
              Cerrar
            </Button>
          </div>
        </div>
      </DialogContent>
    </Dialog>
  )
}
