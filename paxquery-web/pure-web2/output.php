<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="PAXQuery demo">
<title>PAXQuery demo</title>
<link rel="stylesheet" href="css/pure-min.css">
<link rel="stylesheet" href="css/layouts/side-menu.css">
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
                <li><a href="datasets.php">Datasets</a></li>
                <li><a href="index.php">Queries</a></li>
                <li><a href="tps.php">Tree Patterns</a></li>
                <li><a href="algebra.php">Logical Plan</a></li>
                <li><a href="pact.php">Flink Input</a></li>
                <li class="menu-item-divided pure-menu-selected"><a href="output.php">Output</a></li>
            </ul>
        </div>
    </div>

    <div id="main">
        <div class="header">
            <h1>XML Output</h1>
            <!--<h2>A subtitle for your page goes here</h2>-->
        </div>

        <div class="content">
            <div id="XMLHolder" style="display: block; margin-left: -10%; font-size: 12px"> </div>
            <LINK href="css/XMLDisplay.css" type="text/css" rel="stylesheet">
                <script type="text/javascript" src="js/XMLDisplay.js"> </script>
                <script> LoadXML('XMLHolder','../io/final.xml'); </script>
        </div>
    </div>
</div>

<script src="js/ui.js"></script>


</body>
</html>
