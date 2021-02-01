<?php
session_start();

// Website content is now mostly in this tools.php. All php functions are at the bottom of the page.

function headerModule(){
  echo "<header class='mainhead'>
  <img id='ray' src='../../media/raymond.png' alt='Raymond' height=150 /></img>

     <span id='anzac'>ANZAC - The Letters of Douglas Raymond Baker </span>
     <img id='poppy' src='../../media/poppy.jpg' alt='Poppy' height=150 /></img>
     </header>
  <nav class= 'nav'>
   <ul id= 'nav'>
  <li class='norm'><a href='index.php'>Home</a></li>
  <!-- Drop down courtesy of W3 schools -->
  <li class='dropdown'>
      <a href='letters.php#letterlinksheading' class='dropbtn'>Letters</a>
      <div class='dropdown-content'>
      <a href='dynamic.php'>Letters loaded dynamically</a>  
      <a href='letters.php#top'>About the content</a>
        <a href='letters.php#letterlinksheading'>List of Letters </a>
        <a href='letters.php#first'>1914 (3d Transform)</a>
        <a href='letters.php#mid'>1915</a>
        <a href='letters.php#bottom'>1916</a>
      </div>
      </li>
  <li class='norm'><a href='postcards.php'>Postcards</a></li>
  <li class='norm'><a href='places.php'>Places</a></li>
  <li class ='norm'><a href='links.php'>Links</a></li>
  <li class='norm'><a href=contact.php>Contact</a></li>
  <li class='norm'><a href='sitemap.php'>Sitemap</a></li>
  <form action='#' id='usrform'><input type='text' default name='usrname' placeholder='Search not functional'>
    <input type='submit' name='Search' value='Search'>
  </form>
  </ul>
  </nav>";




}


function mainModule($string){


if($string == 'letters'){
  echo  " <article class ='content'>
 <section id = 'info' class = 'letter'>
  <h3>About the content</h3>
    <div>These are copies of letters written by my father Douglas Raymond Baker during the First World War (1914-1918) to his family in Gympie. I have three thick exercise books in which the letters were copied in hand writing by my Aunt Alice, his sister. I understand that this was done so his letters could be sent on to other members of the family. I dont know if the originals are still in existence, probably not.</div>
</br>
    <div>
In the early letters Alice has not included the name of the person they were written to but later she writes at the beginning, Letter to Dot,  Letter to Mother, etc. and later still copies the original, Dear Al, Dear Mag, etc..  Likewise the endings are often not laid out fully as we would do but Al may have done this or Dad may have been saving space on the page.</div>
</br>
<div>Some letters are slightly out of date order in the books but as most of these are around the time his father died I have put them in the correct order as this is crucial to understanding the sense of the contents.  A lot will be missing  many, many ships were sunk but thanks to Aunt Alice we have these copies of the ones that did get through.</div>
</br>
<div>
Where Dad used brackets in a letter I have used [   ]  style and where I have made any comment or explanation I have used (   ) and Italics.</div>
</br>
<div>
Where he uses the word gay it is used in the true sense, bright, happy, carefree, etc., This is the original meaning of the word before it became associated with the homosexual community.</div>
</br>
<div>
The amounts of money are, of course, in Pounds, shillings and pence but as these are out of date and we have no pounds sign in the computer I have written them in, sometimes showing the decimal equivalents. One Pound equalled $2,  one shilling equalled 10 cents and one penny equalled a little under one cent. After a while I stopped putting the equivalents in as they had cno significance unless you knew the relative cost of things then and now. A shilling  equivalent to ten cents might actually alent to ten cents might actually buy  buy ten or twenty dollars worth of goods now.</div>


</section>


<h2 id='letterlinksheading' class='letterlinksheading'> List of all Letters <h2>
         <div> <a class='sitelinks' href ='letters.php#first'>August 28th. 1914.</a></br>
          <a class='sitelinks' href ='letters.php#mid'>Egypt, Jan. 3rd. 1915.</a></br>
          <a class='sitelinks' href ='letters.php#bottom'>April 1st. 1916.</a></br></div>


          <div id =first class = 'parallax2'></div>



      <section id ='letter1' class ='letter3d'>
      <div class='letter3D'>
      <div id='mailfront'>August 28th. 1914.</br> To whom it may concern</br> Care of: Edinborough Barracks</div>
      <div><h3> August 28th. 1914.</h3> </br>We are doing plenty of hard work, the officers here think we will be sent straight into the firing line when we get there and the men all seem eager for it. Taking things altogether the life here is not too bad. All the Gympie fellows are very decent and we stick together. Today we were issued with new rifles and bayonets, etc. but so far no uniform.  My regimental number is 692 but you need not put that in the address of any letters. We were advised to let our people know our number in case  - - - -.

      </br>
      </br>

      Yesterday about 150,000 rounds of ball cartridges arrived, we will have to go through a stiff shooting test. Our feet are examined every day. We sit in a long row with bare feet sticking out. I will have to dress for parade now so hooray with best love from Ray.
      </br><a class='sitelinks' href ='letters.php#letterlinksheading'>>> Back to Letter list</a>
      </div>




  </section>
  <div class = 'parallax2' id ='mid'></div>
  <section id ='letter2' class ='letter'>
  <h3>Egypt, Jan. 3rd. 1915.</h3>

  <div>
  Just a few lines this time as I have already written two letters home  -  one to Dot and one to Alice.
  </div>
  <div> How are things going in Gympie?  I would like to get a glimpse of you all, just to see how you are getting on.  We have the afternoon to ourselves and I am thinking of going for a walk and climbing one of the pyramids after I write a few post cards to various persons.  The letters are very slow in coming here and we just heard that all the Australian mail for Egypt had been taken on to England.  That means that there will be a delay of about three weeks before we get it.  The last letter I received from home was dated Oct 25th.   Are you still at Rankin and Careys?  Anyway I will address this to you there.
</div>
</br>
  <div> Remember me to the Lears, also to other Gympie friends.  We get plenty of work to do here and have not much time for writing.  At night after tea the fellows will not give anyone peace to write, but generally kick up an awful row and throw things at anyone who tries to.  Very often we take up a collection at night and have a supper.  There are canteens here at which we can buy almost anything  fruit cakes, tinned fish, biscuits, lemonade, hot tea or coffee, figs, dates, chocolates and English chestnuts and other nuts.   The niggers bring the daily newspapers to the camp, so we get all the war news. There is plenty of music here, the bugles start at 6.15 a.m. and from then up to 9 p.m. bands and bugles are going all the time. </div>
  <a class='sitelinks' href ='letters.php#letterlinksheading'>>> Back to Letter list</a>
</section>
  <div class = 'parallax2' id ='bottom'></div>
  <section id ='letter3' class = 'letter'>
      <h3>Troopship Corsican, Mediterranean Sea. </h3>
      <h3>April 1st. 1916.</h3>
      <div>






Dear Mother,</br>
                    At last we are bound for France  the chosen home of Chivalry, the garden of romance  where very likely we will have some hotter work to do than we had at Gallipoli.  I hope my last letters have reached you, I wrote to Alice telling her about the Prince of Wales review of the 3rd Brigade and also to you when I sent 20 Pounds from Cairo.  While in Cairo on leave I bought a small camera and have taken a dozen photos so far but have been unable to get them developed, must try in France and will send you some if they turn out any good.  A couple of days after I bought this camera orders came out that they would not be allowed in France, so I will have to get rid of it?  We were very glad to get away from the camp on the Suez Canal bank for the heat was getting unbearable  -  at 5 p.m. on Tuesday last we marched out, crossed the Canal over the pontoon bridge and marched to Serapeum No. 2 siding where, at about 9.30 p.m. we entrained in trucks.  The train proceeded straight through to Alexandria, arriving at the wharf about 8 a.m. .  After much fatigue work shifting kit bags, guns, tripods, belts, ammunition, etc. we embarked on the Corsican, a fine large ship.  Our Coy. were lucky enough to get 2nd class cabins and are very comfortable.  The 11th and 12th Batts. and others are on this boat and most of them are sleeping in hammocks on the lower decks.  We are escorted by the destroyher Rattlesnake which dodges about from side to side and ahead and behind also.  Eight of our guns are mounted in various parts of the ship and we do duty from 4.30 a.m. to 7 p.m., looking out for submarines.  It is now 48 hours since we left Alexandria and everything is alright; yesterday the waves were rather high and tossed us about a bit but today is lovely.  Corsican stopped at Malta for an hour on Sunday, April 2nd</div>
                    <a class='sitelinks' href ='letters.php#letterlinksheading'>>> Back to Letter list</a>
                  </section>
</article>";

}


else if($string == 'links'){
    echo "<article class ='content'>
    <a class='sitelinks' href ='https://www.aif.adfa.edu.au/showPerson?pid=11163'>Summary of Service in the AIF project</a></br>
    <a class='sitelinks' href ='http://recordsearch.naa.gov.au/scripts/Imagine.asp?B=3009496'>His complete original Service Record</a></br>
    <a class='sitelinks' href ='https://www.google.com.au/search?hl=en&site=imghp&tbm=isch&source=hp&biw=1920&bih=982&q=Omrah&oq=Omrah&gs_l=img.12...5422.5422.0.6592.1.1.0.0.0.0.212.212.2-1.1.0.msedr...0...1ac.1.62.img..1.0.0.xuc9Jh0uuzM#imgdii=_&imgrc=_XfTmb11-JZqDM%253A%3BrsnzwSFIEHttOM%3Bhttp%253A%252F%252Fwww.nationalanzaccentre.com.au%252Fsites%252Fdefault%252Ffiles%252Fstyles%252Fcharacter_bite_images%252Fpublic%252Fships%252FH02223--2-.jpg%253Fitok%253Dp6xYoFZI%3Bhttp%253A%252F%252Fwww.nationalanzaccentre.com.au%252Fstory%252Frobert-george-hamilton%3B1363%3B1000'>Picture of the 9th Battallion AIF, boarding the Omrah, Brisbane</a></br>
 </br>


</article>";

}

else if ($string== 'places'){
  echo "<article class= 'content'>
  <div id='map' style='width: 100%'><iframe src='https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d11495787.334985005!2d17.544601962046126!3d33.22418143231711!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1sEnoggera%2C%20%20%20Pinkenba%2C%20%20%20Melbourne%2C%20%20%20Albany%2C%20W.A.%2C%20%20Colombo(Sri-Lanka)%2C%20Aden%2C%20%20%20Egypt%2C%20%20%20Suez%2C%20%20%20Port%20Said%2C%20%20%20Alexandria%2C%20%20%20Cairo%2C%20%20Mena%20Camp%2C%20Kas%C3%AE-el-Nil%20Barracks%2C%20%20%20Lemnos%20Is.%2C%20Dardenelles%2C%20%20Gallipoli%2C%20%20Anzac%2C%20%20Malta%2C%20%20Fort%20Tigne%2C%20%20Slienna%2C%20%20Valetta%2C%20%20Fort%20Ricasoli%2C%20%20Helmich%2C%20%20Zeitoun%2C%20%20Tel-el-Kebir%2C%20Serapeum%2C%20Gab-el-Habieta%2C%20%20%20Marseilles%2C%20%20%20Strazelle%2C%20%20%20Hazebrouck%2C%20%20%20Estaires%2C%20%20Sailley%2C%20%20Baileul%2C%20%20Naours%2C%20%20Godeswanvelde%2C%20%20Doullens%2C%20%20Beauval%2C%20%20Somme%2C%20Bonneville%2C%20%20Halloy-Pernois%2C%20Palmas%2C%20%20%20Rubempre%2C%20Herrincort%2C%20Toutencourt%2C%20%20Harponville%2C%20%20Varrenes%2C%20%20Hedauville%2C%20%20Albert%2C%20%20Pozieres%2C%20%20La%20Boiselle%2C%20%20Contalmaison%2C%20%20Beauval%2C%20%20Proven%2C%20%20Poperinghe%2C%20%20Reninghelst%2C%20%20%20Ypres%2C%20%20Hill%2060%2C%20%20Hellabouk%2C%20%20Boescheppe%2C%20%20Steenvoorde%2C%20%20Cassel%2C%20%20Noordpiene%2C%20%20Liderzelle%2C%20%20Watten%2C%20%20Halleborouck%2C%20%20Le%20Panne%2C%20%20Polincove%2C%20%20Pont-Remy%2C%20%20Abbeville%2C%20%20%20Bellancourt%2C%20%20Amiens%2C%20%20Ailly%2C%20%20Buire%2C%20%20Dernancourt%2C%20%20Melaulte%2C%20%20Longueval%2C%20%20Fleurs%2C%20Trones%20Wood%2C%20%20Bapaume%2C%20%20Fricourt%2C%20%20Croisy%2C%20%20Havre%2C%20%20Henencourt%20Wood%2C%20%20Southampton%2C%20%20%20London%2C%20%20Sutton%2C%20Grantham!5e0!3m2!1sen!2sau!4v1595137458379!5m2!1sen!2sau' width='600' height='450' frameborder='1px'  style='border:0;' allowfullscreen='' aria-hidden='false' tabindex='2'></iframe></div>
  <section id='places'>
    <div>Most of the time the letters take place in Northern Africa</div></br>
    <div>However, there are mentions of Europe, and Australian locations also:</div>
  <p>
  Enoggera,   Pinkenba,   Melbourne,   Albany, W.A.,  Colombo(Sri-Lanka), Aden,   Egypt,   Suez,   Port Said,   Alexandria,   Cairo,  Mena Camp, Kas-el-Nil Barracks,

Lemnos Is., Dardenelles,  Gallipoli,  Anzac,  Malta,  Fort Tigne,  Slienna,  Valetta,  Fort Ricasoli,  Helmich,  Zeitoun,  Tel-el-Kebir, Serapeum, Gab-el-Habieta,

Marseilles,   Strazelle,   Hazebrouck,   Estaires,  Sailley,  Baileul,  Naours,  Godeswanvelde,  Doullens,  Beauval,  Somme, Bonneville,  Halloy-Pernois, Palmas,

Rubempre, Herrincort, Toutencourt,  Harponville,  Varrenes,  Hedauville,  Albert,  Pozieres,  La Boiselle,  Contalmaison,  Beauval,  Proven,  Poperinghe,  Reninghelst,

Ypres,  Hill 60,  Hellabouk,  Boescheppe,  Steenvoorde,  Cassel,  Noordpiene,  Liderzelle,  Watten,  Halleborouck,  Le Panne,  Polincove,  Pont-Remy,  Abbeville,

Bellancourt,  Amiens,  Ailly,  Buire,  Dernancourt,  Melaulte,  Longueval,  Fleurs, Trones Wood,  Bapaume,  Fricourt,  Croisy,  Havre,  Henencourt Wood,  Southampton,

London,  Sutton, Grantham



  </p>
  </section>
  </article>";

}
else if($string == 'postcards'){
  echo "<article class ='content'>
  <h2 id = 'postcardlinks' class='letterlinksheading'> List of all Postcards <h2>
      <a class='sitelinks' href ='postcards.php#firstpost'>Post Card. August 25th 1914.</a></br>
      <a class='sitelinks' href ='postcards.php#midpost'>Post Card, August 31st. 1914.</a></br>
      <a class='sitelinks' href ='postcards.php#botpost'>Post Card. (no date shown)</a></br>

      <div id =firstpost class = 'parallax2'></div>
      <section id ='post1' class ='letter'>
  <h3>August 25th 1914.</h3>
  <div>
  Just have a chance to write a few lines at a small shop near the Camp. We had about four hours in Brisbane before going to Camp and had a good look round. Arrived in Camp about 6 p.m. and all the Gympie lads [Infantry] were placed in one tent [11 of us].  The Light Horse are nearly a mile from us. Of course it was my luck to be made Tent Orderly for the first day  getting the tucker and cleaning up. We have had nothing issued to us yet, so just as well I brought something.
</div></br>
  <div>There is no childs play in this camp  plenty of hard work  they mean business. We have a free hand at night so far and can write as we like.  Saw Herb as we were marching to the Camp. He was going back from work and I had only time to shake hands with him  could not leave the ranks. We will be under Captain Jackson. He has been appointed Captain of the Northern Rivers men and managed to get us in with him. Cannot write any more, am in a hurry. Will write more later on. Am feeling quite homesick. Had our feet examined today, teeth tomorrow I think.
</div>
<a class='sitelinks' href ='postcards.php#postcardlinks'>>> Back to Post-card list</a>
</section>
<div class = 'parallax2' id ='midpost'></div>
<section id ='post2' class ='letter'>
<h3>August 31st. 1914.</h3>
<div>
On Saturday when we came into camp from drill at 12 oclock, we were told that the Commandant was very pleased with the progress made and had granted us a half holiday so the Gympie lads decided to go to the city. We had a good time in Brisbane and had tea at a fish shop.  Met Herb at about 7 p.m. and we all went to the Empire but the show was very poor. We have had issued to us two towels, two pairs under-pants, two singlets, two pairs socks, a sweater and a cake of soap. I think we will get boots in a day or two and other things.</div></br>

      <div>A church parade was held but none of the Gympie lads attended. It happened to be a voluntary one! We are all keeping in pretty good health and hope you all are at home. Today is payday, we are to get paid every fortnight, this time we will get 11 days. I am only drawing half of mine and banking the rest. Love to all. </div>

      <a class='sitelinks' href ='postcards.php#postcardlinks'>>> Back to Post-card list</a>
</section>
<div class = 'parallax2' id ='botpost'></div>
<section id ='post3' class ='letter'>
<h3>September  1914.</h3>
<div>Your letters are very welcome and I always look forward to them.  We will not be leaving here before next Tuesday, so I will be able to receive a few more yet, will I not?  Mother was out to the camp today with Jennie, they brought some cakes and eggs which were very welcome.  After seeing Mum off I met Kate Jory who was here with some other girls. I had about five minutes talk with her but had to slide off as our Coy. was being paid and I did not want to be late for that.  Things are going on just the same as usual and nothing exciting occurs in our camp life. There was a bayonet fixing and unfixing competition held today and the prize was ten shillings which was won by H Coy.. Sorry to say our lads did not come up to the mark at that game, but on the whole Col. Lee was quite satisfied.  Every morning before breakfast the whole of the Infantry go for a couple of miles march  -  it is much better than drilling and gives us an appetite for breakfast.  I have not much news to tell you Dot, and not much time either so will close with the best of love to you all.</div>
<a class='sitelinks' href ='postcards.php#postcardlinks'>>> Back to Post-card list</a>
</section>
</article>";

}
else{
  echo "<article class= 'content' id='Welcome'>
  <div id = 'intro'> INTRODUCTION -  Discover the ANZAC spirit!</div>
  <p>
  Hello and welcome,
  </p>
  <p>
  This year is the centenary of the birth of the ANZAC legend. As such, many people, particularly young people, are looking for ways to connect with people of that period and inparticular, those who created the ANZAC legend.
  </p>
  <p>
  This site presents the letters of Douglas Raymond Baker, who came from Gympie, Queensland, Australia. He enlisted in August 1914 and during the years that followed, he wrote letters and post cards to his family at home. In them, he describes much of the goings-on of the life of an ANZAC, his feeling and opinions, and experiences while visiting his relatives in England during leave.
  </p>
  <p>
  They start from the beginning of basic training in Brisbane in August 1914 and end in May 1918.
  </p>
  <p>
  They are offered here so that others may get an understanding of life as an ANZAC and get a sense of what life on the battlefield was like.
  </p>
  <p>
  From the menu at the top, read the Introduction under Letters, to set the scene. Then, to start reading the letters, click on Letter and Post Cards in the menu at the top. All the letters are searchable using the  search bar at the top right of this page.
  </p>

  </article>";

}
}
















  function checkName($name, &$errorName, &$checksGood){

    if(empty($name)){
            $errorName='Please enter a Name';

      }else {
        $pattern = "/^[a-zA-Z \-.']+$/";
        if(preg_match($pattern,$name)){
          $name = filter_var($name, FILTER_SANITIZE_STRING, FILTER_FLAG_STRIP_LOW | FILTER_FLAG_STRIP_HIGH);
          $checksGood++;


        }else {

          $errorName="Please enter an appropriate Name (Alphabetic and ' . - characters only)";
    }
  }

  }

  function checkEmail($email, &$errorEmail, &$checksGood){

    if(empty($email)){
            $errorEmail='Please enter an Email (__@___.___)';

    }else{
      if(!filter_var($email, FILTER_VALIDATE_EMAIL)) {

        $cleanEmail=filter_var($email, FILTER_SANITIZE_EMAIL);
        $errorEmail="Did you want to type $cleanEmail? Please enter a valid email (__@___.___)";


      }else{
        $checksGood++;
      }
    }
    }





  function checkPhone($mobile, &$errorPhone, &$mobileBoolean){
    if (empty($mobile)){
            $errorPhone='';
            return $mobileBoolean=true;

        

        }else {
          $pattern = "/^(04|\+614|\(04\)){1,1}( ?\d){8}$/";
          if(preg_match($pattern,$mobile)){
            $errorPhone='';
            return $mobileBoolean=true;
            
          }else{
          $errorPhone='Please enter an Australian Mobile number (Starting with 04, +614 or (04)';
          return $mobileBoolean=false;
    }
  }

  }


  function cleanSubject($subject, &$errorSubject, &$checksGood){
    if(empty($subject)){
      $errorSubject='Please enter a subject';



    }else{
      
      $subject = rtrim(preg_replace('/[\x00-\x1F\x80-\xFF]/u', '', $subject));
      // Leaving this line here Jeffry so you can see I could have used Filter_var instead of a pattern
      $subject = filter_var($subject, FILTER_SANITIZE_STRING, FILTER_FLAG_STRIP_LOW | FILTER_FLAG_STRIP_HIGH);
     
      $checksGood++;

    }
  }

  function cleanMessage($message, &$errorMessage, &$checksGood){
    if(empty($message)){
      $errorMessage='Please enter a message';


    }else {
      $message = rtrim(preg_replace('/[\x00-\x1F\x80-\xFF]/u', '', $message));
       // Leaving this line here Jeffry so you can see I could have used Filter_var instead of a pattern
      $message = filter_var($message, FILTER_SANITIZE_STRING, FILTER_FLAG_STRIP_LOW | FILTER_FLAG_STRIP_HIGH);
      
      $checksGood++;
      
  }
}





 

  function loadLetters(){
    $fp = fopen("http://titan.csit.rmit.edu.au/~e54061/wp/letters-home.txt",'r');
    flock($fp, LOCK_SH);
    if (($headings = fgetcsv($fp, 0, "\t")) !== false) {
      while ( $cells = fgetcsv($fp, 0, "\t") ) {
        for ($x=1; $x<count($cells); $x++)
        $letters[$cells[0]][$headings[$x]]=$cells[$x];
        
            
  }
}
  flock($fp, LOCK_UN);
  fclose($fp);
  foreach($letters as $x => $x_value) {
    echo "Date: " , $x;
    echo "<br>";
    foreach($x_value as $c => $content) {
      
      if(!empty($content)){
        $con = str_replace("&emsp;", '', $content);
        echo $c, " : ", nl2br($con);
        echo "<br>";
      }
    }
    echo "<br>";
    echo "<br>";
    echo "<br>";
  }
  


  


}

/* function not used anymore, keeping this in tools in case it becomes useful
function printLetter($string){
  //clean loaded data before printing
  $string = rtrim(preg_replace('/[\x00-\x1F\x80-\xFF]/u', '', $string));
  //$string = filter_var(FILTER_SANITIZE_STRING, FILTER_FLAG_STRIP_LOW | FILTER_FLAG_STRIP_HIGH);
  echo $string;
  echo "</br>";
  echo "</br>";
  
}*/
?>