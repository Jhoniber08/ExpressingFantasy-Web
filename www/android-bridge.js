/* Expressing Fantasy — puente Android (solo actúa dentro de la APK de Capacitor; en el navegador no hace nada).
   Pausa el audio cuando sales de la app y lo reanuda al volver. No toca la jugabilidad. */
(function(){
  if(!window.Capacitor)return;
  var A=window.AudioContext||window.webkitAudioContext; if(!A)return;
  var L=[];
  function W(o){var c=o===undefined?new A():new A(o);L.push(c);return c}
  W.prototype=A.prototype;
  try{Object.setPrototypeOf(W,A)}catch(e){}
  window.AudioContext=W; if(window.webkitAudioContext)window.webkitAudioContext=W;
  function set(v){for(var i=0;i<L.length;i++){try{var c=L[i];if(v){if(c.state==='suspended')c.resume()}else if(c.state==='running')c.suspend()}catch(e){}}}
  window.__efAppVis=set;
  document.addEventListener('visibilitychange',function(){set(!document.hidden)});
})();
