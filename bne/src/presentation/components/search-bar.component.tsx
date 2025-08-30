"use client"

import type React from "react"

import { useState } from "react"
import { Search } from "lucide-react"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"

interface SearchBarProps {
  value: string
  onChange: (value: string) => void
}

export function SearchBar({ value, onChange }: SearchBarProps) {
  const [localValue, setLocalValue] = useState(value)

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    onChange(localValue)
  }

  return (
    <form onSubmit={handleSubmit} className="flex gap-2">
      <div className="relative flex-1">
        <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 h-4 w-4 text-muted-foreground" />
        <Input
          type="text"
          placeholder="Profesión, empresa o palabra clave*"
          value={localValue}
          onChange={(e) => setLocalValue(e.target.value)}
          className="pl-10"
        />
      </div>
      <Button type="submit" className="px-8">
        BUSCAR
      </Button>
    </form>
  )
}
