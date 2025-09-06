import type { JobRepository } from "../../domain/repositories/job-repository.interface"
import type { JobOffer, JobSearchCriteria } from "../../domain/entities/job-offer"

export class MockJobRepository implements JobRepository {
  private mockJobs: JobOffer[] = [
    {
      id: "1",
      title: "TERAPEUTA OCUPACIONAL",
      company: "Confidencial",
      location: "La Araucanía - Temuco",
      description:
        "Promover la inclusión de personas en situación de discapacidad y/o vulnerabilidad social en el mercado laboral, a través de la atención, orientación y evaluación...",
      requirements: "Título profesional en Terapia Ocupacional",
      publishedDate: new Date(),
      type: "bne",
      contractType: "indefinido",
      workSchedule: "completa",
      educationLevel: "superior-completa",
      origin: "ofertas-bne",
      category: "Salud",
    },
    {
      id: "2",
      title: "Administrativo RRHH",
      company: "REPORT LTDA",
      location: "Bío Bío - Talcahuano",
      description:
        "Profesional que realizara funciones acorde con el cargo relacionadas con el área de RRHH, integrara un área consolidada.",
      requirements: "Experiencia en área de RRHH",
      publishedDate: new Date(Date.now() - 86400000),
      type: "bne",
      contractType: "plazo-fijo",
      workSchedule: "completa",
      educationLevel: "superior-completa",
      origin: "ofertas-bne",
      category: "Administración",
    },
    {
      id: "3",
      title: "Ejecutivo de ventas call center",
      company: "Confidencial",
      location: "Metropolitana - Santiago",
      description:
        "Importante empresa busca Ejecutivo de ventas call center, sin experiencia. Funciones: Ventas para diversas campañas como lo es Entel y VTR...",
      requirements: "Sin experiencia requerida",
      publishedDate: new Date(),
      type: "external",
      contractType: "indefinido",
      workSchedule: "completa",
      educationLevel: "media-completa",
      origin: "portales-empleo",
      category: "Ventas",
    },
  ]

  async searchJobs(criteria: JobSearchCriteria): Promise<{
    jobs: JobOffer[]
    totalCount: number
    currentPage: number
    totalPages: number
  }> {
    let filteredJobs = [...this.mockJobs]

    // Filter by job type (bne vs external)
    if (criteria.query?.includes("bne")) {
      filteredJobs = filteredJobs.filter((job) => job.type === "bne")
    } else if (criteria.query?.includes("external")) {
      filteredJobs = filteredJobs.filter((job) => job.type === "external")
    }

    // Apply other filters
    if (criteria.region) {
      filteredJobs = filteredJobs.filter((job) => job.location.toLowerCase().includes(criteria.region!.toLowerCase()))
    }

    if (criteria.tipoContrato) {
      filteredJobs = filteredJobs.filter((job) => job.contractType === criteria.tipoContrato)
    }

    if (criteria.jornadaLaboral) {
      filteredJobs = filteredJobs.filter((job) => job.workSchedule === criteria.jornadaLaboral)
    }

    if (criteria.nivelEducativo) {
      filteredJobs = filteredJobs.filter((job) => job.educationLevel === criteria.nivelEducativo)
    }

    if (criteria.origenOferta) {
      filteredJobs = filteredJobs.filter((job) => job.origin === criteria.origenOferta)
    }

    // Search by query
    if (criteria.query && !criteria.query.includes("bne") && !criteria.query.includes("external")) {
      filteredJobs = filteredJobs.filter(
        (job) =>
          job.title.toLowerCase().includes(criteria.query!.toLowerCase()) ||
          job.company.toLowerCase().includes(criteria.query!.toLowerCase()) ||
          job.description.toLowerCase().includes(criteria.query!.toLowerCase()),
      )
    }

    const pageSize = criteria.pageSize || 10
    const currentPage = criteria.page || 1
    const totalCount = filteredJobs.length
    const totalPages = Math.ceil(totalCount / pageSize)

    const startIndex = (currentPage - 1) * pageSize
    const jobs = filteredJobs.slice(startIndex, startIndex + pageSize)

    return {
      jobs,
      totalCount,
      currentPage,
      totalPages,
    }
  }

  async getJobById(id: string): Promise<JobOffer | null> {
    return this.mockJobs.find((job) => job.id === id) || null
  }
}
