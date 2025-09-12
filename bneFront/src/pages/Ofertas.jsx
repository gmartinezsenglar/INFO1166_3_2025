// src/pages/Ofertas.jsx
import { useEffect, useMemo, useState } from "react";
import { getOfferCards, getFacets } from "../api/offers";
import "../css/ofertas.css"; // <-- importa tu css

function hoyAyer(fechaStr) {
  if (!fechaStr) return null;
  const f = new Date(fechaStr);
  const h = new Date();
  f.setHours(0,0,0,0); h.setHours(0,0,0,0);
  const diff = Math.round((h - f) / 86400000);
  return diff === 0 ? "Hoy" : diff === 1 ? "Ayer" : null;
}

export default function OfertasPage() {
  const [origen, setOrigen] = useState("ALL");
  const [q, setQ] = useState("");
  const [discapacidad, setDiscapacidad] = useState(false);
  const [region, setRegion] = useState("");
  const [ciudad, setCiudad] = useState("");
  const [jornada, setJornada] = useState("");
  const [contrato, setContrato] = useState("");
  const [nivelEducativo, setNivelEducativo] = useState("");
  const [nivelCargo, setNivelCargo] = useState("");
  const [desde, setDesde] = useState("");
  const [hasta, setHasta] = useState("");

  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10);

  const [facets, setFacets] = useState(null);
  const [data, setData] = useState({ content: [], page: 0, size: 10, totalElements: 0, totalPages: 0 });
  const [loading, setLoading] = useState(false);

  const appliedCount = useMemo(() =>
    [q, discapacidad ? "1" : "", region, ciudad, jornada, contrato, nivelEducativo, nivelCargo, desde, hasta]
      .filter(Boolean).length
  , [q, discapacidad, region, ciudad, jornada, contrato, nivelEducativo, nivelCargo, desde, hasta]);

  useEffect(() => { getFacets().then(setFacets); }, []);

  useEffect(() => {
    const params = {
      origen,
      q: q || undefined, region: region || undefined, ciudad: ciudad || undefined,
      jornada: jornada || undefined, contrato: contrato || undefined,
      nivelEducativo: nivelEducativo || undefined, nivelCargo: nivelCargo || undefined,
      discapacidad: discapacidad || undefined,
      desde: desde || undefined, hasta: hasta || undefined,
      page, size, sort: "fechaPublicacion,desc"
    };
    setLoading(true);
    getOfferCards(params).then(setData).finally(() => setLoading(false));
  }, [origen, q, region, ciudad, jornada, contrato, nivelEducativo, nivelCargo, discapacidad, desde, hasta, page, size]);

  const reset = () => {
    setOrigen("ALL"); setQ(""); setDiscapacidad(false); setRegion(""); setCiudad("");
    setJornada(""); setContrato(""); setNivelEducativo(""); setNivelCargo("");
    setDesde(""); setHasta(""); setPage(0); setSize(10);
  };

  const regiones = facets?.regiones ?? [];
  const comunas = (region && facets?.comunasPorRegion?.[region]) ? facets.comunasPorRegion[region] : [];
  const jornadas = facets?.jornadas ?? [];
  const contratos = facets?.tiposContrato ?? [];
  const nivelesEdu = facets?.nivelesEducativos ?? [];
  const nivelesCargo = facets?.nivelesCargo ?? [];

  return (
    <div className="ofertas-layout">
      {/* Sidebar filtros */}
      <aside className="ofertas-sidebar">
        <div className="ofertas-sidebar__header">
          <h3>Filtros</h3>
          <button className="ofertas-reset-btn" onClick={reset}>Reiniciar filtros</button>
        </div>

        <div className="ofertas-field">
          <input
            className="ofertas-input"
            type="text"
            placeholder="Buscar por título/empresa…"
            value={q}
            onChange={e => { setQ(e.target.value); setPage(0); }}
          />
        </div>

        <div className="ofertas-field">
          <label>
            <input
              className="ofertas-checkbox"
              type="checkbox"
              checked={discapacidad}
              onChange={e => { setDiscapacidad(e.target.checked); setPage(0); }}
              style={{ width: "auto" }}
            />
            &nbsp;Puestos con ajustes razonables
          </label>
        </div>

        <div className="ofertas-field">
          <label>Fecha de publicación</label>
          <div className="ofertas-row">
            <input className="ofertas-input" type="date" value={desde} onChange={e=>{setDesde(e.target.value); setPage(0);}} />
            <input className="ofertas-input" type="date" value={hasta} onChange={e=>{setHasta(e.target.value); setPage(0);}} />
          </div>
        </div>

        <div className="ofertas-field">
          <label>Región</label>
          <select className="ofertas-select" value={region} onChange={e=>{setRegion(e.target.value); setCiudad(""); setPage(0);}}>
            <option value="">Seleccionar</option>
            {regiones.map(r => <option key={r} value={r}>{r}</option>)}
          </select>
        </div>

        <div className="ofertas-field">
          <label>Comuna</label>
          <select className="ofertas-select" value={ciudad} onChange={e=>{setCiudad(e.target.value); setPage(0);}} disabled={!region}>
            <option value="">Seleccionar</option>
            {comunas.map(c => <option key={c} value={c}>{c}</option>)}
          </select>
        </div>

        <div className="ofertas-field">
          <label>Nivel de cargo</label>
          <select className="ofertas-select" value={nivelCargo} onChange={e=>{setNivelCargo(e.target.value); setPage(0);}}>
            <option value="">Seleccionar</option>
            {nivelesCargo.map(n => <option key={n} value={n}>{n}</option>)}
          </select>
        </div>

        <div className="ofertas-field">
          <label>Nivel educativo</label>
          <select className="ofertas-select" value={nivelEducativo} onChange={e=>{setNivelEducativo(e.target.value); setPage(0);}}>
            <option value="">Seleccionar</option>
            {nivelesEdu.map(n => <option key={n} value={n}>{n}</option>)}
          </select>
        </div>

        <div className="ofertas-field">
          <label>Jornada laboral</label>
          <select className="ofertas-select" value={jornada} onChange={e=>{setJornada(e.target.value); setPage(0);}}>
            <option value="">Seleccionar</option>
            {jornadas.map(j => <option key={j} value={j}>{j}</option>)}
          </select>
        </div>

        <div className="ofertas-field">
          <label>Tipo de contrato</label>
          <select className="ofertas-select" value={contrato} onChange={e=>{setContrato(e.target.value); setPage(0);}}>
            <option value="">Seleccionar</option>
            {contratos.map(c => <option key={c} value={c}>{c}</option>)}
          </select>
        </div>

        <div className="ofertas-field">
          <label>Origen de la oferta</label>
          <select className="ofertas-select" value={origen} onChange={e=>{setOrigen(e.target.value); setPage(0);}}>
            <option value="ALL">Todos</option>
            <option value="BNE">Ofertas BNE</option>
            <option value="EXTERNA">Ofertas Externas</option>
          </select>
        </div>

        <div className="ofertas-applied">
          Filtros aplicados: {appliedCount}
        </div>
      </aside>

      {/* Listado */}
      <main>
        <div className="ofertas-header">
          <h3 className="ofertas-title">{loading ? "Cargando..." : `${data?.totalElements ?? 0} ofertas de empleo`}</h3>
          <div className="ofertas-page-size">
            <span>Resultados por página:</span>
            <select value={size} onChange={e=>{setSize(Number(e.target.value)); setPage(0);}}>
              <option value={10}>10</option>
              <option value={20}>20</option>
              <option value={50}>50</option>
            </select>
          </div>
        </div>

        <div style={{ marginTop: 12 }}>
          {!loading && data?.content?.map(o => {
            const badge = hoyAyer(o.fechaPublicacion);
            return (
              <div key={`${o.origen}-${o.id}`} className="oferta-card">
                <div className="oferta-card__top">
                  <div>
                    {badge && <span className="oferta-badge">{badge}</span>}
                    <span className="oferta-title">{o.titulo}</span>
                  </div>
                  <button className="oferta-vermas">Ver Más</button>
                </div>
                <div className="oferta-desc">{o.descripcionCorta}</div>
                <div className="oferta-meta">
                  <b>{o.empresa || "Confidencial"}</b> &nbsp; {o.region} - {o.ciudad}
                </div>
              </div>
            );
          })}
        </div>

        {/* Paginación */}
        {!loading && data && (
          <div className="ofertas-pagination">
            <button className="ofertas-page-btn" disabled={page===0} onClick={()=>setPage(p=>p-1)}>Anterior</button>
            <span> Página {data.page + 1} de {data.totalPages} </span>
            <button className="ofertas-page-btn" disabled={data.page + 1 >= data.totalPages} onClick={()=>setPage(p=>p+1)}>Siguiente</button>
          </div>
        )}
      </main>
    </div>
  );
}
