"use client";

import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Checkbox } from "@/components/ui/checkbox";
import {
  FECHA_PUBLICACION_OPTIONS,
  REGION_OPTIONS,
  NIVEL_EDUCATIVO_OPTIONS,
  JORNADA_LABORAL_OPTIONS,
  TIPO_CONTRATO_OPTIONS,
  GRUPO_EMPLEO_OPTIONS,
  ORIGEN_OFERTA_BNE_OPTIONS,
  ORIGEN_OFERTA_EXTERNAL_OPTIONS,
} from "../../domain/value-objects/filter-options";
import type { JobSearchCriteria } from "../../domain/entities/job-offer";

interface SearchFiltersProps {
  filters: Omit<JobSearchCriteria, "query" | "page" | "pageSize">;
  onFiltersChange: (
    filters: Omit<JobSearchCriteria, "query" | "page" | "pageSize">
  ) => void;
  jobType: "bne" | "external";
}

export function SearchFilters({
  filters,
  onFiltersChange,
  jobType,
}: SearchFiltersProps) {
  console.log("[v0] SearchFilters - jobType:", jobType);

  const handleFilterChange = (key: string, value: string) => {
    onFiltersChange({
      ...filters,
      [key]: value,
    });
  };

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
    });
  };

  const origenOptions =
    jobType === "bne"
      ? ORIGEN_OFERTA_BNE_OPTIONS
      : ORIGEN_OFERTA_EXTERNAL_OPTIONS;

  console.log("[v0] SearchFilters - origenOptions:", origenOptions);

  return (
    <Card>
      <CardHeader>
        <div className="flex items-center justify-between">
          <CardTitle className="text-sm font-medium">Filtros</CardTitle>
          <Button variant="outline" size="sm" onClick={resetFilters}>
            Reiniciar filtros
          </Button>
        </div>
      </CardHeader>
      <CardContent className="space-y-4">
        {jobType === "bne" && (
          <>
            {/* Checkbox for disability-friendly jobs */}
            <div className="flex items-center space-x-2">
              <Checkbox id="disability" />
              <label htmlFor="disability" className="text-xs">
                Puestos de trabajos con ajustes razonables para personas con
                discapacidad
              </label>
            </div>

            {/* Date filter */}
            <div>
              <label className="text-sm font-medium mb-2 block">
                Fecha de publicación
              </label>
              <Select
                value={filters.fechaPublicacion}
                onValueChange={(value) =>
                  handleFilterChange("fechaPublicacion", value)
                }
              >
                <SelectTrigger>
                  <SelectValue placeholder="Seleccionar" />
                </SelectTrigger>
                <SelectContent>
                  {FECHA_PUBLICACION_OPTIONS.map((option) => (
                    <SelectItem key={option.value} value={option.value}>
                      {option.label}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            {/* Region filter */}
            <div>
              <label className="text-sm font-medium mb-2 block">Región</label>
              <Select
                value={filters.region}
                onValueChange={(value) => handleFilterChange("region", value)}
              >
                <SelectTrigger>
                  <SelectValue placeholder="Seleccionar" />
                </SelectTrigger>
                <SelectContent>
                  {REGION_OPTIONS.map((option) => (
                    <SelectItem key={option.value} value={option.value}>
                      {option.label}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            {/* Comuna filter */}
            <div>
              <label className="text-sm font-medium mb-2 block">Comuna</label>
              <Select
                value={filters.comuna}
                onValueChange={(value) => handleFilterChange("comuna", value)}
              >
                <SelectTrigger>
                  <SelectValue placeholder="Seleccionar" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="santiago">Santiago</SelectItem>
                  <SelectItem value="valparaiso">Valparaíso</SelectItem>
                  <SelectItem value="concepcion">Concepción</SelectItem>
                </SelectContent>
              </Select>
            </div>

            {/* Occupation filter */}
            <div>
              <label className="text-sm font-medium mb-2 block">
                Ocupación
              </label>
              <Select
                value={filters.ocupacion}
                onValueChange={(value) =>
                  handleFilterChange("ocupacion", value)
                }
              >
                <SelectTrigger>
                  <SelectValue placeholder="Seleccionar" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="salud">Salud</SelectItem>
                  <SelectItem value="educacion">Educación</SelectItem>
                  <SelectItem value="tecnologia">Tecnología</SelectItem>
                  <SelectItem value="administracion">Administración</SelectItem>
                </SelectContent>
              </Select>
            </div>

            {/* Employment group filter */}
            <div>
              <label className="text-sm font-medium mb-2 block">
                Grupo de empleo
              </label>
              <Select
                value={filters.grupoEmpleo}
                onValueChange={(value) =>
                  handleFilterChange("grupoEmpleo", value)
                }
              >
                <SelectTrigger>
                  <SelectValue placeholder="Seleccionar" />
                </SelectTrigger>
                <SelectContent>
                  {GRUPO_EMPLEO_OPTIONS.map((option) => (
                    <SelectItem key={option.value} value={option.value}>
                      {option.label}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            {/* Education level filter */}
            <div>
              <label className="text-sm font-medium mb-2 block">
                Nivel educativo
              </label>
              <Select
                value={filters.nivelEducativo}
                onValueChange={(value) =>
                  handleFilterChange("nivelEducativo", value)
                }
              >
                <SelectTrigger>
                  <SelectValue placeholder="Seleccionar" />
                </SelectTrigger>
                <SelectContent>
                  {NIVEL_EDUCATIVO_OPTIONS.map((option) => (
                    <SelectItem key={option.value} value={option.value}>
                      {option.label}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            {/* Work schedule filter */}
            <div>
              <label className="text-sm font-medium mb-2 block">
                Jornada laboral
              </label>
              <Select
                value={filters.jornadaLaboral}
                onValueChange={(value) =>
                  handleFilterChange("jornadaLaboral", value)
                }
              >
                <SelectTrigger>
                  <SelectValue placeholder="Seleccionar" />
                </SelectTrigger>
                <SelectContent>
                  {JORNADA_LABORAL_OPTIONS.map((option) => (
                    <SelectItem key={option.value} value={option.value}>
                      {option.label}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            {/* Contract type filter */}
            <div>
              <label className="text-sm font-medium mb-2 block">
                Tipo de contrato
              </label>
              <Select
                value={filters.tipoContrato}
                onValueChange={(value) =>
                  handleFilterChange("tipoContrato", value)
                }
              >
                <SelectTrigger>
                  <SelectValue placeholder="Seleccionar" />
                </SelectTrigger>
                <SelectContent>
                  {TIPO_CONTRATO_OPTIONS.map((option) => (
                    <SelectItem key={option.value} value={option.value}>
                      {option.label}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>
          </>
        )}

        {/* Origin filter - always shown but with different options for BNE vs External */}
        <div>
          <label className="text-sm font-medium mb-2 block">
            Origen de la Oferta
          </label>
          <Select
            value={filters.origenOferta}
            onValueChange={(value) => handleFilterChange("origenOferta", value)}
          >
            <SelectTrigger>
              <SelectValue placeholder="Seleccionar" />
            </SelectTrigger>
            <SelectContent>
              {origenOptions.map((option) => (
                <SelectItem key={option.value} value={option.value}>
                  {option.label}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>

        <div className="text-xs text-muted-foreground">
          Filtros aplicados: {Object.values(filters).filter(Boolean).length}
        </div>
      </CardContent>
    </Card>
  );
}
