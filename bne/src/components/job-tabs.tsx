"use client"

import { Button } from "@/components/ui/button"

interface JobTabsProps {
  activeTab: "bne" | "external"
  onTabChange: (tab: "bne" | "external") => void
}

export function JobTabs({ activeTab, onTabChange }: JobTabsProps) {
  return (
    <div className="flex border-b">
      <Button
        variant={activeTab === "bne" ? "default" : "ghost"}
        className={`rounded-none border-b-2 ${
          activeTab === "bne"
            ? "border-primary bg-primary text-primary-foreground"
            : "border-transparent hover:border-muted-foreground"
        }`}
        onClick={() => onTabChange("bne")}
      >
        Ofertas BNE
      </Button>
      <Button
        variant={activeTab === "external" ? "default" : "ghost"}
        className={`rounded-none border-b-2 ${
          activeTab === "external"
            ? "border-primary bg-primary text-primary-foreground"
            : "border-transparent hover:border-muted-foreground"
        }`}
        onClick={() => onTabChange("external")}
      >
        Ofertas Externas
      </Button>
    </div>
  )
}
