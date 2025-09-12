import api from "./client";

export async function privatePing(token) {
  const res = await api.get("/private/ping", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return res.data;
}
