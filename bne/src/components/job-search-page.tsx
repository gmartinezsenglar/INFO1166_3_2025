"use client"

import { useState } from "react"
import { SearchFilters } from "./search-filters"
import { JobListings } from "./job-listings"
import { JobTabs } from "./job-tabs"
import { Breadcrumb } from "./breadcrumb"
import { SearchBar } from "./search-bar"

export function JobSearchPage() {
  const [activeTab, setActiveTab] = useState<"bne" | "external">("bne")
  const [searchQuery, setSearchQuery] = useState("")
  const [filters, setFilters] = useState({
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
        <Breadcrumb />

        <div className="mt-6">
          <SearchBar value={searchQuery} onChange={setSearchQuery} />
        </div>

        <div className="mt-6">
          <JobTabs activeTab={activeTab} onTabChange={setActiveTab} />
        </div>

        <div className="mt-6 grid grid-cols-1 lg:grid-cols-4 gap-6">
          <div className="lg:col-span-1">
            <SearchFilters filters={filters} onFiltersChange={setFilters} activeTab={activeTab} />
          </div>

          <div className="lg:col-span-3">
            <JobListings searchQuery={searchQuery} filters={filters} activeTab={activeTab} />
          </div>
        </div>
      </div>

      {/* Footer */}
      <footer className="bg-muted mt-12 py-8">
        <div className="container mx-auto px-4">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8 text-sm">
            <div>
              <h3 className="font-semibold mb-3">Links de interés</h3>
              <ul className="space-y-1 text-muted-foreground">
                <li>
                  <a href="#" className="hover:text-foreground">
                    Política de privacidad
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-foreground">
                    Ley 19.728 (Seguro de Cesantía)
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-foreground">
                    Ley 21.015 inclusión de personas con discapacidad
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-foreground">
                    Manual de usuario
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-foreground">
                    Preguntas Frecuentes-FAQ
                  </a>
                </li>
              </ul>
            </div>
            <div>
              <h3 className="font-semibold mb-3">Instituciones relacionadas</h3>
              <ul className="space-y-1 text-muted-foreground">
                <li>
                  <a href="#" className="hover:text-foreground">
                    Ministerio del Trabajo
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-foreground">
                    Dirección del Trabajo
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-foreground">
                    SENCE
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-foreground">
                    AFC
                  </a>
                </li>
                <li>
                  <a href="#" className="hover:text-foreground">
                    Senadis
                  </a>
                </li>
              </ul>
            </div>
            <div>
              <div className="text-center">
                <p className="text-muted-foreground">2 2405 5200</p>
                <p className="text-muted-foreground">soporte@bne.cl</p>
              </div>
            </div>
          </div>
        </div>
      </footer>
    </div>
  )
}
