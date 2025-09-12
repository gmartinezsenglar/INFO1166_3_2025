// Infra: implementación que llama a tu backend
export type SearchJobsParams = {
  region?: string;
  ciudad?: string;
  pagaMinima?: number;
  pagaMaxima?: number;
  reqExperiencia?: boolean;
  pagina?: number; // si usas pageable
  tamaño?: number; // size
};

// Tipo del dominio (ajústalo a tu carpeta domain/entities)
export type Job = {
  id: number;
  titulo: string;
  descripcion?: string;
  region?: string;
  ciudad?: string;
  pagaMinima?: number;
  pagaMaxima?: number;
  tipoContrato?: string;
  nivelCargo?: string;
  empresaNombre?: string;
};

function fromBackendToJob(x: any): Job {
  // adapta el JSON real de tu backend
  return {
    id: x.id ?? x.ofertaId ?? 0,
    titulo: x.nombre ?? x.titulo ?? 'Sin título',
    descripcion: x.descripcion,
    region: x.region,
    ciudad: x.ciudad,
    pagaMinima: x.pagaMinima,
    pagaMaxima: x.pagaMaxima,
    tipoContrato: x.tipoContrato,
    nivelCargo: x.nivelCargo,
    empresaNombre: x.empresa?.nombre ?? x.empresaNombre,
  };
}

export class HttpJobRepository {
  private base = process.env.NEXT_PUBLIC_BASE ?? ''; // opcional

  async search(params: SearchJobsParams): Promise<Job[]> {
    const qs = new URLSearchParams();
    Object.entries(params).forEach(([k, v]) => {
      if (v !== undefined && v !== null && v !== '') qs.set(k, String(v));
    });

    // usamos el rewrite: va a http://localhost:8080/dev/ofertas-bne
    const res = await fetch(`${this.base}/api/dev/ofertas-bne?${qs}`, {
      cache: 'no-store',
    });
    if (!res.ok) throw new Error('Error buscando ofertas');

    const data = await res.json();
    // si tu backend devuelve {content:[...]} por paginación:
    const list = Array.isArray(data) ? data : (data.content ?? []);
    return list.map(fromBackendToJob);
  }

  async getById(id: number): Promise<Job | null> {
    const res = await fetch(`${this.base}/api/dev/ofertas-bne/${id}`, {
      cache: 'no-store',
    });
    if (res.status === 404) return null;
    if (!res.ok) throw new Error('Error obteniendo oferta');
    const data = await res.json();
    return fromBackendToJob(data);
  }
}
