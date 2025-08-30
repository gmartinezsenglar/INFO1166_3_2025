import { ChevronRight } from "lucide-react"

interface BreadcrumbProps {
  currentPage: string
}

export function Breadcrumb({ currentPage }: BreadcrumbProps) {
  return (
    <nav className="flex items-center space-x-2 text-sm text-muted-foreground">
      <a href="/" className="hover:text-foreground">
        Inicio
      </a>
      <ChevronRight className="h-4 w-4" />
      <span className="text-foreground">{currentPage}</span>
    </nav>
  )
}
