import api from "./client";

// Lista paginada de cards
export async function getOfferCards(params) {
  const res = await api.get("/api/ofertas/cards", { params });
  return res.data; // { content, page, size, totalElements, totalPages, appliedFilters }
}

// Facets para combos (regiones, comunas, jornadas, etc.)
export async function getFacets() {
  const res = await api.get("/api/ofertas/facets");
  return res.data; // { regiones, comunasPorRegion, jornadas, tiposContrato, nivelesEducativos, nivelesCargo }
}
