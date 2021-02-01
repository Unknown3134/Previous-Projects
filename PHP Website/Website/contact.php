
<?php require 'tools.php'; 

# var error messages, declared as blank until proven guilty
$errorStatus='';

$errorEmail='';
$errorPhone='';
$errorPhone='';
$errorSubject='';
$errorMessage='';
$errorEmail='';
$errorName='';
#Global error counter, it takes just 1 error to make checks not pass
$checksGood=0;
#As Mobile is not a requirement, a boolean is initialised as True, and only becomes false
# if a bad phone number is entered.
$mobileBoolean=true;


if (!empty($_POST)) {
  
  /*this line is unnecessary because of individual filters, however it's here for reference
  $_POST  = filter_input_array(INPUT_POST, FILTER_SANITIZE_STRING); */
  
  // extracts Post array using id as variable names
  extract($_POST);
 

  
// Checks all extracted form variables
  checkName($name, $errorName, $checksGood);
  checkEmail($email, $errorEmail, $checksGood);
  checkPhone($mobile, $errorPhone, $mobileBoolean);
  cleanSubject($subject, $errorSubject, $checksGood);
  cleanMessage($message, $errorMessage, $checksGood);
  
  
}
  

// If all checks are good, success message and post is saved to mail.txt

if ($checksGood == 4 && $mobileBoolean)  {
  $_SESSION = $_POST;
  $errorStatus='Your form was successfully submitted!';
  
  $keys=array_keys($_POST);

  $file = "/home/sl1/S3791201/public_html/wp/a3/mail.txt"; 

  $file = fopen($file, 'a');
  fputcsv($file, $keys, "\t"); 
  fputcsv($file, $_POST, "\t");
  fclose($file);
} 



?>













<!DOCTYPE html>
<html lang='en'>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ANZAC - The Letters of Douglas Raymond Baker</title>
    <meta name='description' content="Contact page for ANZAC site">
    <meta name='keywords' content="HTML, CSS">
    <meta name='author' content='Paul McKercher'> 
    <!-- Keep wireframe.css for debugging, add your css to style.css -->
    <link id='wireframecss' type="text/css" rel="stylesheet" href="../wireframe.css" disabled>
    <link id='stylecss' type="text/css" rel="stylesheet" href="style.css?t=<?= filemtime("style.css"); ?>">
    <script src='../wireframe.js'></script>
    

 
  </head>

  <body onload= "rememberMeLoad()">
  <?php headerModule()?>
  



<main>

<article class ='content'>
<span class='errorStatus'><?php echo $errorStatus;?></span>  
<h3 id= 'contacthead'> Contact Form </h3>
<form name ='contactme' id='contact' action='contact.php' method='post'>
<label for='name'>Name</label>
<div><input type='text' name='name' id='name' placeholder='Your Name' pattern="^[a-zA-Z \-.']+$" title="Please Enter a Name (only use A-Z Alphabetic characters and ' . -)" required /><span class= 'error'><?php echo $errorName;?></span></div>
<label for='email'>Email</label>
<div><input type='email' name='email' id='email' placeholder='Your Email' title= "Please Enter an Email address" required/><span class= 'error'><?php echo $errorEmail;?></span></div>
<label for='mobile'>Mobile Phone</label>
<div><input type='tel' name='mobile' id='mobile' placeholder='Your Mobile' title="Please Enter an Australian mobile (04 , +614 or (04) Only)" pattern ="^(04|\+614|\(04\)){1,1}( ?\d){8}$" /><span class= 'error'><?php echo $errorPhone;?></span></div>
<label for ='subject'>Subject</label>
<div><input type='text' name='subject' id='subject' placeholder='Your Subject' title= "Please enter a Subject" required/><span class= 'error'><?php echo $errorSubject;?></span></div>
<label for='message'>Message</label>
<div><textarea id='message' name='message'  rows="20" cols="40" input='message' title= "Please enter a Message" required></textarea> <span class= 'error' id='messagebox'><?php echo $errorMessage;?></span></div>
<div></div>

<div></div>
<div></div>
<div><input id= 'submit' type ='submit' value ='Submit'/>
</div>

</form>
<input type='checkbox'  id='rememberMe' onchange ="rememberMe()"/><label id='rememberlabel' for='rememberMe'>Remember Me</label>
</article>
</main>
</body>

<footer>
      <div><span><a class='links' href='sitemap.php'>Site Map</a></span><span><a class='links' href='mailto:s3791201@student.rmit.edu.au '>Send me an email</a></span></div>
</br>
      <div>&copy;<script>
        document.write(new Date().getFullYear());
      </script> Paul McKercher s3791201   Last modified <?= date ("Y F d  H:i", filemtime($_SERVER['SCRIPT_FILENAME'])); ?>.</div>
      <div>Disclaimer: This website is not a real website and is being developed as part of a School of Science Web Programming course at
    
    <div>RMIT University in Melbourne, Australia.</div><button id='toggleWireframeCSS' onclick='toggleWireframe()'>Toggle Wireframe CSS</button>
    <div></div> 
    
    </footer>
    <script src="tools.js"></script>
    
    <!--pattern ="^(04|\+614|\(04\)){1,1}( ?\d){8}$" -->