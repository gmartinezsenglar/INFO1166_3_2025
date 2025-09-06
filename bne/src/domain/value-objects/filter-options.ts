export const FECHA_PUBLICACION_OPTIONS = [
  { value: "hoy", label: "Hoy" },
  { value: "ayer", label: "Ayer" },
  { value: "menor-3-dias", label: "Menor a 3 días" },
  { value: "menor-1-semana", label: "Menor a 1 semana" },
  { value: "menor-15-dias", label: "Menor a 15 días" },
  { value: "menor-1-mes", label: "Menor a 1 mes" },
  { value: "menor-2-meses", label: "Menor a 2 meses" },
] as const

export const REGION_OPTIONS = [
  { value: "metropolitana", label: "Metropolitana" },
  { value: "arica-parinacota", label: "Arica y Parinacota" },
  { value: "tarapaca", label: "Tarapacá" },
  { value: "antofagasta", label: "Antofagasta" },
  { value: "atacama", label: "Atacama" },
  { value: "coquimbo", label: "Coquimbo" },
  { value: "valparaiso", label: "Valparaíso" },
  { value: "ohiggins", label: "O'Higgins" },
  { value: "maule", label: "Maule" },
  { value: "nuble", label: "Ñuble" },
  { value: "bio-bio", label: "Bío Bío" },
  { value: "araucania", label: "La Araucanía" },
  { value: "los-rios", label: "Los Ríos" },
  { value: "los-lagos", label: "Los Lagos" },
  { value: "aysen", label: "Aysén" },
  { value: "magallanes", label: "Magallanes y Antártica Chilena" },
] as const

export const NIVEL_EDUCATIVO_OPTIONS = [
  { value: "sin-educacion", label: "Sin Educación formal" },
  { value: "basica-incompleta", label: "Educación básica incompleta" },
  { value: "basica-completa", label: "Educación básica completa" },
  { value: "media-incompleta", label: "Educación media incompleta" },
  { value: "media-completa", label: "Educación media completa" },
  { value: "superior-incompleta", label: "Educación superior Incompleta" },
  { value: "superior-completa", label: "Educación superior Completa" },
  { value: "magister", label: "Magíster" },
  { value: "educacion-especial", label: "Educación especial" },
  { value: "doctorado", label: "Doctorado" },
] as const

export const JORNADA_LABORAL_OPTIONS = [
  { value: "completa", label: "Jornada Completa" },
  { value: "parcial", label: "Jornada Parcial/part time" },
  { value: "sin-limitacion", label: "Sin limitación horaria" },
] as const

export const TIPO_CONTRATO_OPTIONS = [
  { value: "obra-faena", label: "Contrato por obra o faena" },
  { value: "plazo-fijo", label: "Contrato a plazo Fijo" },
  { value: "indefinido", label: "Contrato indefinido" },
  { value: "admin-publica", label: "Administración Pública" },
  { value: "honorarios", label: "Honorarios" },
  { value: "indiferente", label: "Indiferente" },
  { value: "practica", label: "Acuerdo de práctica" },
  { value: "aprendizaje", label: "Contrato de aprendizaje" },
  { value: "sin-contrato", label: "Sin Contrato" },
] as const

export const GRUPO_EMPLEO_OPTIONS = [
  { value: "indigenas", label: "Indígenas" },
  { value: "migrantes", label: "Migrantes" },
  { value: "adultos-mayores", label: "Adultos mayores" },
  { value: "infractores-ley", label: "Personas infractoras de ley" },
  { value: "jovenes", label: "Jóvenes" },
  { value: "mujer", label: "Mujer" },
] as const

export const ORIGEN_OFERTA_BNE_OPTIONS = [
  { value: "todas", label: "Todas" },
  { value: "ofertas-bne", label: "Ofertas BNE" },
  { value: "convenio-mop", label: "Convenio MOP" },
  { value: "er", label: "ER" },
  { value: "portales-empleo", label: "Portales de Empleo" },
  { value: "empleo-publico", label: "Empleo público" },
] as const

export const ORIGEN_OFERTA_EXTERNAL_OPTIONS = [
  { value: "todas", label: "Todas" },
  { value: "feria", label: "Feria" },
  { value: "api", label: "API" },
  { value: "portales-empleo", label: "Portales de empleo" },
  { value: "empleo-publico", label: "Empleo público" },
] as const
