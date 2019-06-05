
	function show(bloq) {
	 obj = document.getElementById(bloq);
	 obj.style.display = (obj.style.display=='none') ? 'block' : 'none';
         if(obj.id=="usuario"){
             obj2=document.getElementById("alministrador");
             obj2.style.display = 'none';
         }
          if(obj.id=="alministrador"){
             obj2=document.getElementById("usuario");
             obj2.style.display = 'none';
         }
}


