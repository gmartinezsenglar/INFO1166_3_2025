"use client"

import { Search } from "lucide-react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"

interface SearchBarProps {
  value: string
  onChange: (value: string) => void
}

export function SearchBar({ value, onChange }: SearchBarProps) {
  return (
    <div className="flex gap-2">
      <div className="flex-1 relative">
        <Input
          type="text"
          placeholder="Profesión, empresa o palabra clave*"
          value={value}
          onChange={(e) => onChange(e.target.value)}
          className="pr-10"
        />
        <Search className="absolute right-3 top-1/2 transform -translate-y-1/2 h-4 w-4 text-muted-foreground" />
      </div>
      <Button className="bg-primary hover:bg-primary/90">BUSCAR</Button>
    </div>
  )
}
