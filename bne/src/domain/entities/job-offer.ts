export interface JobOffer {
  id: string
  title: string
  company: string
  location: string
  salary?: string
  description: string
  requirements: string
  publishedDate: Date
  type: "bne" | "external"
  contractType: string
  workSchedule: string
  educationLevel: string
  origin: string
  category: string
}

export interface JobSearchCriteria {
  query?: string
  fechaPublicacion?: string
  region?: string
  comuna?: string
  ocupacion?: string
  grupoEmpleo?: string
  nivelEducativo?: string
  jornadaLaboral?: string
  tipoContrato?: string
  origenOferta?: string
  page?: number
  pageSize?: number
}
