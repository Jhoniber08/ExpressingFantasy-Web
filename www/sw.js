/* Expressing Fantasy — service worker: permite instalar el juego y jugar sin conexión */
const CACHE='ef-v11-2';
const CORE=['./','./index.html','./manifest.webmanifest','./icon-192.png','./icon-512.png','./three.min.js','./peerjs.min.js','./fonts/fonts.css'];
self.addEventListener('install',e=>{e.waitUntil(caches.open(CACHE).then(c=>Promise.all(CORE.map(u=>c.add(new Request(u,{mode:u.startsWith('http')?'no-cors':'same-origin'})).catch(()=>{})))).then(()=>self.skipWaiting()))});
self.addEventListener('activate',e=>{e.waitUntil(caches.keys().then(ks=>Promise.all(ks.filter(k=>k!==CACHE).map(k=>caches.delete(k)))).then(()=>self.clients.claim()))});
self.addEventListener('fetch',e=>{const r=e.request;if(r.method!=='GET')return;const u=new URL(r.url);
 if(u.hostname.endsWith('peerjs.com'))return;
 // la página: primero red (para recibir actualizaciones), si no hay conexión usa la copia guardada
 if(r.mode==='navigate'){e.respondWith(fetch(r).then(res=>{const c=res.clone();caches.open(CACHE).then(ca=>ca.put('./index.html',c));return res}).catch(()=>caches.match('./index.html')));return}
 e.respondWith(caches.match(r).then(hit=>hit||fetch(r).then(res=>{if(res.ok||res.type==='opaque'){const c=res.clone();caches.open(CACHE).then(ca=>ca.put(r,c))}return res}).catch(()=>hit)))});
