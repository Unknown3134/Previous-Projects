<?php require 'tools.php'; ?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ANZAC - The Letters of Douglas Raymond Baker</title>
    <meta name='description' content="The Letters of Douglas Raymond Baker, during World War 1">
    <meta name='keywords' content="HTML, CSS">
    <meta name='author' content='Paul McKercher'>
    
    <!-- Keep wireframe.css for debugging, add your css to style.css -->
    <link id='wireframecss' type="text/css" rel="stylesheet" href="../wireframe.css" disabled>
    <link id='stylecss' type="text/css" rel="stylesheet" href="style.css?t=<?= filemtime("style.css"); ?>">
    <script src='../wireframe.js'></script>
</head>
<body>

<?php headerModule();?>


<main>
<?php mainModule('links');?>
</main>
</body>
<footer>
      <div id><span><a class='links' href='sitemap.php'>Site Map</a></span><span><a class='links' href='mailto:s3791201@student.rmit.edu.au '>Send me an email</a></span></div>
</br>
      <div>&copy;<script>
        document.write(new Date().getFullYear());
      </script> Paul McKercher s3791201   Last modified <?= date ("Y F d  H:i", filemtime($_SERVER['SCRIPT_FILENAME'])); ?>.</div>
      <div>Disclaimer: This website is not a real website and is being developed as part of a School of Science Web Programming course at
    
    <div>RMIT University in Melbourne, Australia.</div><button id='toggleWireframeCSS' onclick='toggleWireframe()'>Toggle Wireframe CSS</button>
    <div></div> 
    </footer>