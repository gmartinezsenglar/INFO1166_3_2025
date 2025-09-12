import api from "./client";

export async function getToken(user) {
  const res = await api.post(`/auth/token?user=${user}`);
  // si viene { token: "..." } toma esa propiedad; si viene string, úsalo tal cual
  return typeof res.data === "string" ? res.data : res.data.token;
}
