import { ChevronRight } from "lucide-react"

export function Breadcrumb() {
  return (
    <nav className="flex items-center space-x-2 text-sm text-muted-foreground">
      <a href="#" className="hover:text-foreground">
        Inicio
      </a>
      <ChevronRight className="h-4 w-4" />
      <span className="text-foreground">Ofertas de empleo</span>
    </nav>
  )
}
