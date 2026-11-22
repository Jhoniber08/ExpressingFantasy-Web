# Expressing Fantasy — APK Android (v11.1)

Juego 3D de mundo abierto **Expressing Fantasy v11.1**, empaquetado como app Android con Capacitor.
El juego es exactamente el mismo que la versión web v11.1; solo se cambiaron las librerías externas por copias locales para que funcione sin internet.

**Creadores:** Jhoniber De la Cruz y Justin Manuel Nolasco «Mano».

## Descargar la APK

👉 **https://github.com/Jhoniber08/ExpressingFantasy-Web/releases/latest/download/ExpressingFantasy-Web.apk**

Cada push a `main` compila una APK nueva (GitHub Actions) firmada siempre con la misma clave, así que se instala encima de la anterior sin perder la partida.

## Qué funciona sin internet
- Todo el juego (Three.js 0.158.0, fuentes Cinzel y Nunito Sans y PeerJS van incluidos en `www/`).
- Necesitan internet: el **multijugador** (servidor de PeerJS / WebRTC) y el **guardado en la nube** opcional (Firebase).

## Estructura
- `www/` — el juego web v11.1 (index.html, three.min.js, peerjs.min.js, fuentes, iconos).
- `android/` — proyecto Android de Capacitor 6 (horizontal, pantalla completa inmersiva, aceleración por hardware).
- `keystore/expressingfantasy.p12` — clave de firma permanente (alias y contraseña en el workflow).
- `.github/workflows/android.yml` — compila, firma y publica la APK en Releases.

## Créditos
- Juego: Jhoniber De la Cruz y Justin Manuel Nolasco «Mano».
- Three.js (MIT), PeerJS (MIT), fuentes Cinzel y Nunito Sans (SIL Open Font License, en `www/fonts/`).
