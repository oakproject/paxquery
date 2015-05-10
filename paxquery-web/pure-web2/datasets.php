<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="PAXQuery demo">
<title>PAXQuery demo</title>
<link rel="stylesheet" href="css/pure-min.css">
<link rel="stylesheet" href="css/layouts/side-menu.css">
<link rel="stylesheet" href="css/custom.css">
</head>
<body>
<div id="layout">
    <!-- Menu toggle -->
    <a href="#menu" id="menuLink" class="menu-link">
        <!-- Hamburger icon -->
        <span></span>
    </a>

    <div id="menu">
        <div class="pure-menu pure-menu-open">
            <a class="pure-menu-heading" href="#">PAXQuery</a>

            <ul>
              <li class="menu-item-divided pure-menu-selected">
                    <a href="datasets.php">Datasets</a></li>
                <li><a href="index.php">Queries</a></li>
                <li><a href="tps.php">Tree Patterns</a></li>
                <li><a href="algebra.php">Logical Plan</a></li>
                <li><a href="pact.php">Flink Input</a></li>
                <li><a href="output.php">Output</a></li>
            </ul>
        </div>
    </div>

    <div id="main">
        <div class="header">
            <h1>Datasets</h1>
            <!--<h2>A subtitle for your page goes here</h2>-->
        </div>

        <div class="content pure-form pure-form-stacked">
            <!--<form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post" class="pure-form pure-form-stacked">-->
<fieldset>

      <label for="dataset">Dataset</label>


      <select class="form-control" id="dataset" name="dataset" style="width: 100%">
        <!--<option value="forum" <?= ($querysubmitted && $_POST['dataset'] == "forum") ? 'selected' : '' ?>>Forum</option>
        <option value="synthetic" <?= ($querysubmitted && $_POST['dataset'] == "synthetic") ? 'selected' : '' ?>>Synthetic</option>-->
        <option value="forum" selected>Forum</option>
        <option value="synthetic">Synthetic</option>
      </select>

      <!--<div style="background-color: #B8DBFF; border-radius: 3px;">-->
      <div id="content_forum" style="display: block">
        <p style="width:100%; display: block; margin-left: auto; margin-right: auto; text-align: center;">
          <a href="img/1007.png" target="_blank"><img src="./img/1007.png" alt="" style="width:32%; display: inline; margin-left: auto; margin-right: auto"/></a>
          <a href="img/210.png" target="_blank"><img src="img/210.png" alt="" style="width:32%; display: inline; margin-left: auto; margin-right: auto"/></a>
          <a href="img/875.png" target="_blank"><img src="img/875.png" alt="" style="width:32%; display: inline; margin-left: auto; margin-right: auto"/></a>
        </p>

        <div>
          <div style="display: inline; width: 90%; float: left; vertical-align: middle;">
            <p><h2>Set of web pages crawled from <a style="text-decoration: none; font-weight: normal; color: #FA7D17;" href="http://www.bikeforums.net/forum.php">http://www.bikeforums.net/forum.php</a></h2></p>
            <p><h2>Provided by The Internet Memory Fundation</h2></p>
            <p><h2 style="display: inline;">Format</h2>: XHTML (HTML compliant with XML standards), can be navigated and queried like XML</h2></p>
            <p><h2 style="display: inline; color: #FA7D17;">PAXQuery can be used for extracting different parts of interest</h2></p>

          </div>
          <div style="display: inline; width: 10%; float: right;">
            <a href="http://internetmemory.org/en/" target="_blank"><img src="img/imlogo.png" alt="" style="width:100%; display: inline; margin-left: auto; margin-right: auto;"/></a>           
          </div>
        </div>

        <div style="clear: both;">
        </div>  

        <div>
          <div style="display: inline; width: 75%; float: left;">
            <a href="img/parts2.png" target="_blank"><img src="./img/parts2.png" alt="" style="width:100%; display: inline; margin-left: auto; margin-right: auto"/></a>
          </div>
          <div style="display: inline; width: 25%; float: left; vertical-align: middle;">
            <strong>
            <ul style="list-style-type: none; vertical-align:middle; ">
              <li style="padding: 10px;">Thread title</li>
              <li style="padding: 10px;">Post date</li>
              <li style="padding: 10px;">Post author</li>
              <li style="padding: 10px;">Author profile</li>
              <li style="padding: 10px;">Post content, etc</li>
            </ul>
            </strong>
          </div>
        </div>
      <br>
      <p style="clear: both; padding-top: 10px;">  
       <h2 style="display: inline;">Results</h2> are useful for:
        <strong>
        <ul style="color: #FA7D17;">
          <li>Understanding user profiles</li>
          <li>Analyzing user interests</li>
          <li>Bussiness strategy</li>
          <li>Displaying target-oriented ads</li>
        </ul>
      </strong>
      </p>
    </div>
      <div id="content_synthetic" style="display: none">
        <p style="width:100%; display: block; margin-left: auto; margin-right: auto; text-align: center;">
          <!--<img src="img/full-boxes.png" alt="" style="width:32%; display: inline; margin-left: auto; margin-right: auto"/>
          <img src="img/person.png" alt="" style="width:32%; display: inline; margin-left: auto; margin-right: auto"/>
          <img src="img/item.png" alt="" style="width:32%; display: inline; margin-left: auto; margin-right: auto"/>
          <img src="img/ca.png" alt="" style="width:32%; display: inline; margin-left: auto; margin-right: auto"/>-->
          <a href="img/full-boxes.png" target="_blank"><img src="img/full-boxes.png" alt="" style="width:51%; display: inline; margin-left: auto; margin-right: auto"/></a>
          <a href="img/ca.png" target="_blank"><img src="img/ca.png" alt="" style="width:44%; display: inline; margin-left: auto; margin-right: auto"/></a>
        </p>
        <div>
          <div style="display: inline; width: 75%; float: left; vertical-align: middle;">
            <h2>Artificial dataset, generated in-house at INRIA.<br> Emulation of internal data at an auction house.</h2>
            <p><h2 style="display: inline;">Format</h2>: XML</h2></p>
          </div>
           <div style="display: inline; width: 25%; float: right;">
            <a href="http://www.inria.fr/" target="_blank"><img src="img/inria.png" alt="" style="width:100%; display: inline; margin-left: auto; margin-right: auto;"/></a>           
          </div>
        </div>
        <div style="margin-left: auto; margin-right: auto;">
          <div style="clear: both;">
            <div style="display: inline; width: 40%; float: left;">
              <strong style="color: #FA7D17;">People</strong>
              <ul>
                <li>ID</li>
                <li>Name</li>
                <li>Profile: email, income, age, etc</li>
                <li>Watches other open auctions</li>
                <li>Others</li>
              </ul>
            </div>
            <div style="display: inline; width: 40%; float: left;">
              <strong style="color: #FA7D17;">Items</strong>
              <ul>
                <li>ID</li>
                <li>Description</li>
                <li>Location</li>
                <li>Quantity</li>
                <li>Others</li>
              </ul>
            </div>
          </div>
          <div style="clear: both;">
            <div style="display: inline; width: 40%; float: left;">
              <strong style="color: #FA7D17;">Closed Auctions</strong>
              <ul>
                <li>Item ID</li>
                <li>Seller ID</li>
                <li>Buyer ID</li>
                <li>Price</li>
                <li>Others</li>
              </ul>
            </div>
            <div style="display: inline; width: 40%; float: left;">
              <strong style="color: #FA7D17;">Open Auctions</strong>
              <ul>
                <li>Open Auction ID</li>
                <li>Item ID</li>
                <li>Seller ID</li>
                <li>Initial price</li>
                <li>Reserve price</li>
                <li>Current price</li>
                <li>Bids</li>
                <li>Others</li>
              </ul>
            </div>
          </div>
        </div>

      </div>
    <!--</div>-->
</fieldset>


</div>
</div>
</div>





<script src="js/ui.js"></script>
<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/queries.js"></script>
<script>
    $(document).ready(function () {

    $("#dataset").change(function () {
      switch($("#dataset").val()) {
        case "forum":
          document.getElementById("content_forum").style.display = 'block';
          document.getElementById("content_synthetic").style.display = 'none';
          break;
        case "synthetic":
          document.getElementById("content_forum").style.display = 'none';
          document.getElementById("content_synthetic").style.display = 'block';
          break;
      }
    });
  });
</script>


</body>
</html>
