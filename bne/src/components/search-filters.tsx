"use client"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Button } from "@/components/ui/button"
import { Checkbox } from "@/components/ui/checkbox"

interface SearchFiltersProps {
  filters: {
    fechaPublicacion: string
    region: string
    comuna: string
    ocupacion: string
    grupoEmpleo: string
    nivelEducativo: string
    jornadaLaboral: string
    tipoContrato: string
    origenOferta: string
  }
  onFiltersChange: (filters: any) => void
  activeTab: "bne" | "external"
}

export function SearchFilters({ filters, onFiltersChange, activeTab }: SearchFiltersProps) {
  const updateFilter = (key: string, value: string) => {
    onFiltersChange({ ...filters, [key]: value })
  }

  const resetFilters = () => {
    onFiltersChange({
      fechaPublicacion: "",
      region: "",
      comuna: "",
      ocupacion: "",
      grupoEmpleo: "",
      nivelEducativo: "",
      jornadaLaboral: "",
      tipoContrato: "",
      origenOferta: "",
    })
  }

  return (
    <Card>
      <CardHeader>
        <div className="flex items-center justify-between">
          <CardTitle className="text-lg">Filtros</CardTitle>
          <Button variant="outline" size="sm" onClick={resetFilters}>
            Reiniciar filtros
          </Button>
        </div>
      </CardHeader>
      <CardContent className="space-y-4">
        {/* Checkbox para discapacidad */}
        <div className="flex items-center space-x-2">
          <Checkbox id="discapacidad" />
          <label htmlFor="discapacidad" className="text-sm">
            Puestos de trabajos con ajustes razonables para personas con discapacidad
          </label>
        </div>

        {/* Fecha de publicación */}
        <div>
          <label className="text-sm font-medium mb-2 block">Fecha de publicación</label>
          <Select value={filters.fechaPublicacion} onValueChange={(value) => updateFilter("fechaPublicacion", value)}>
            <SelectTrigger>
              <SelectValue placeholder="Seleccionar" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="hoy">Hoy</SelectItem>
              <SelectItem value="ayer">Ayer</SelectItem>
              <SelectItem value="menor-3-dias">Menor a 3 días</SelectItem>
              <SelectItem value="menor-1-semana">Menor a 1 semana</SelectItem>
              <SelectItem value="menor-15-dias">Menor a 15 días</SelectItem>
              <SelectItem value="menor-1-mes">Menor a 1 mes</SelectItem>
              <SelectItem value="menor-2-meses">Menor a 2 meses</SelectItem>
            </SelectContent>
          </Select>
        </div>

        {/* Región */}
        <div>
          <label className="text-sm font-medium mb-2 block">Región</label>
          <Select value={filters.region} onValueChange={(value) => updateFilter("region", value)}>
            <SelectTrigger>
              <SelectValue placeholder="Seleccionar" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="metropolitana">Metropolitana</SelectItem>
              <SelectItem value="arica-parinacota">Arica y Parinacota</SelectItem>
              <SelectItem value="tarapaca">Tarapacá</SelectItem>
              <SelectItem value="antofagasta">Antofagasta</SelectItem>
              <SelectItem value="atacama">Atacama</SelectItem>
              <SelectItem value="coquimbo">Coquimbo</SelectItem>
              <SelectItem value="valparaiso">Valparaíso</SelectItem>
              <SelectItem value="ohiggins">O'Higgins</SelectItem>
              <SelectItem value="maule">Maule</SelectItem>
              <SelectItem value="nuble">Ñuble</SelectItem>
              <SelectItem value="bio-bio">Bío Bío</SelectItem>
              <SelectItem value="la-araucania">La Araucanía</SelectItem>
              <SelectItem value="los-rios">Los Ríos</SelectItem>
              <SelectItem value="los-lagos">Los Lagos</SelectItem>
              <SelectItem value="aysen">Aysén</SelectItem>
              <SelectItem value="magallanes">Magallanes y Antártica Chilena</SelectItem>
            </SelectContent>
          </Select>
        </div>

        {/* Comuna */}
        <div>
          <label className="text-sm font-medium mb-2 block">Comuna</label>
          <Select value={filters.comuna} onValueChange={(value) => updateFilter("comuna", value)}>
            <SelectTrigger>
              <SelectValue placeholder="Seleccionar" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="santiago">Santiago</SelectItem>
              <SelectItem value="providencia">Providencia</SelectItem>
              <SelectItem value="las-condes">Las Condes</SelectItem>
              <SelectItem value="vitacura">Vitacura</SelectItem>
              <SelectItem value="valparaiso">Valparaíso</SelectItem>
              <SelectItem value="vina-del-mar">Viña del Mar</SelectItem>
            </SelectContent>
          </Select>
        </div>

        {/* Ocupación */}
        <div>
          <label className="text-sm font-medium mb-2 block">Ocupación</label>
          <Select value={filters.ocupacion} onValueChange={(value) => updateFilter("ocupacion", value)}>
            <SelectTrigger>
              <SelectValue placeholder="Seleccionar" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="administracion">Administración</SelectItem>
              <SelectItem value="ventas">Ventas</SelectItem>
              <SelectItem value="ingenieria">Ingeniería</SelectItem>
              <SelectItem value="salud">Salud</SelectItem>
              <SelectItem value="educacion">Educación</SelectItem>
              <SelectItem value="construccion">Construcción</SelectItem>
            </SelectContent>
          </Select>
        </div>

        {/* Grupo de empleo */}
        <div>
          <label className="text-sm font-medium mb-2 block">Grupo de empleo</label>
          <Select value={filters.grupoEmpleo} onValueChange={(value) => updateFilter("grupoEmpleo", value)}>
            <SelectTrigger>
              <SelectValue placeholder="Seleccionar" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="indigenas">Indígenas</SelectItem>
              <SelectItem value="migrantes">Migrantes</SelectItem>
              <SelectItem value="adultos-mayores">Adultos mayores</SelectItem>
              <SelectItem value="personas-infractoras">Personas infractoras de ley</SelectItem>
              <SelectItem value="jovenes">Jóvenes</SelectItem>
              <SelectItem value="mujer">Mujer</SelectItem>
            </SelectContent>
          </Select>
        </div>

        {/* Nivel educativo */}
        <div>
          <label className="text-sm font-medium mb-2 block">Nivel educativo</label>
          <Select value={filters.nivelEducativo} onValueChange={(value) => updateFilter("nivelEducativo", value)}>
            <SelectTrigger>
              <SelectValue placeholder="Seleccionar" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="sin-educacion">Sin Educación formal</SelectItem>
              <SelectItem value="basica-incompleta">Educación básica incompleta</SelectItem>
              <SelectItem value="basica-completa">Educación básica completa</SelectItem>
              <SelectItem value="media-incompleta">Educación media incompleta</SelectItem>
              <SelectItem value="media-completa">Educación media completa</SelectItem>
              <SelectItem value="superior-incompleta">Educación superior incompleta</SelectItem>
              <SelectItem value="superior-completa">Educación superior completa</SelectItem>
              <SelectItem value="magister">Magíster</SelectItem>
              <SelectItem value="doctorado">Doctorado</SelectItem>
            </SelectContent>
          </Select>
        </div>

        {/* Jornada laboral */}
        <div>
          <label className="text-sm font-medium mb-2 block">Jornada laboral</label>
          <Select value={filters.jornadaLaboral} onValueChange={(value) => updateFilter("jornadaLaboral", value)}>
            <SelectTrigger>
              <SelectValue placeholder="Seleccionar" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="completa">Jornada Completa</SelectItem>
              <SelectItem value="parcial">Jornada Parcial/part time</SelectItem>
              <SelectItem value="sin-limitacion">Sin limitación horaria</SelectItem>
            </SelectContent>
          </Select>
        </div>

        {/* Tipo de contrato */}
        <div>
          <label className="text-sm font-medium mb-2 block">Tipo de contrato</label>
          <Select value={filters.tipoContrato} onValueChange={(value) => updateFilter("tipoContrato", value)}>
            <SelectTrigger>
              <SelectValue placeholder="Seleccionar" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="obra-faena">Contrato por obra o faena</SelectItem>
              <SelectItem value="plazo-fijo">Contrato a plazo Fijo</SelectItem>
              <SelectItem value="indefinido">Contrato indefinido</SelectItem>
              <SelectItem value="administracion-publica">Administración Pública</SelectItem>
              <SelectItem value="honorarios">Honorarios</SelectItem>
              <SelectItem value="indiferente">Indiferente</SelectItem>
              <SelectItem value="practica">Acuerdo de práctica</SelectItem>
              <SelectItem value="aprendizaje">Contrato de aprendizaje</SelectItem>
              <SelectItem value="sin-contrato">Sin Contrato</SelectItem>
            </SelectContent>
          </Select>
        </div>

        {/* Origen de la oferta */}
        <div>
          <label className="text-sm font-medium mb-2 block">Origen de la Oferta</label>
          <Select value={filters.origenOferta} onValueChange={(value) => updateFilter("origenOferta", value)}>
            <SelectTrigger>
              <SelectValue placeholder="Seleccionar" />
            </SelectTrigger>
            <SelectContent>
              {activeTab === "bne" ? (
                <>
                  <SelectItem value="todas">Todas</SelectItem>
                  <SelectItem value="ofertas-bne">Ofertas BNE</SelectItem>
                  <SelectItem value="convenio-mop">Convenio MOP</SelectItem>
                  <SelectItem value="er">ER</SelectItem>
                  <SelectItem value="portales-empleo">Portales de Empleo</SelectItem>
                  <SelectItem value="empleo-publico">Empleo público</SelectItem>
                </>
              ) : (
                <>
                  <SelectItem value="todas">Todas</SelectItem>
                  <SelectItem value="feria">Feria</SelectItem>
                  <SelectItem value="api">API</SelectItem>
                  <SelectItem value="portales-empleo">Portales de empleo</SelectItem>
                  <SelectItem value="empleo-publico">Empleo público</SelectItem>
                </>
              )}
            </SelectContent>
          </Select>
        </div>
      </CardContent>
    </Card>
  )
}
