
// Loads with body of contact.php. Checks for rememberMe checkbox ticked
function rememberMeLoad(){
  
  if (localStorage.rememberMe)  {
    document.getElementById("rememberMe").checked = true;
    document.getElementById('name').value =localStorage.name;
    document.getElementById('email').value =localStorage.email;
    document.getElementById('mobile').value =localStorage.mobile;
    
    
  } else {
    document.getElementById("rememberMe").checked = false;
    
    
    

    }
  
  }



// On change of RememberMe checkbox, this function runs to save to localStorage
function rememberMe(){
  
if (document.getElementById('rememberMe').checked)
{
  localStorage.rememberMe=true;
  localStorage.name = document.getElementById('name').value;
  localStorage.email = document.getElementById('email').value;
  localStorage.mobile = document.getElementById('mobile').value;
} else {
  localStorage.clear();
  document.getElementById('name').value = '';
  document.getElementById('email').value = '';
  document.getElementById('mobile').value = '';
  }

}




