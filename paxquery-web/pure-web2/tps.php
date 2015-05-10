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
                <li class="menu-item-divided pure-menu-selected"><a href="tps.php">Tree Patterns</a></li>
                <li><a href="algebra.php">Logical Plan</a></li>
                <li><a href="pact.php">Flink Input</a></li>
                <li><a href="output.php">Output</a></li>
            </ul>
        </div>
    </div>

    <div id="main">
        <div class="header">
            <h1>Navigation Tree Patterns</h1>
            <!--<h2>A subtitle for your page goes here</h2>-->
        </div>

        <div class="content">
            <!--<form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post" class="pure-form pure-form-aligned">-->

<!--<div style="float: center;">-->
<?php
    $images_folder = '/Applications/XAMPP/xamppfiles/htdocs/paxquery/paxquery-installation/web/images/plans';
    $img_path = '../paxquery-installation/web/images/plans';

    //scan for tp* files
    foreach(glob($images_folder.'/tp*.png') as $file) {
        //print "<legend><h1>"."TP"."</h1></legend>";
        print "<div style=\"border-bottom: 1px solid #E5E5E5;\">";
        print "<img src=\"".$img_path."/".basename($file)."\" alt=\"\" style=\"display: block; margin-left: auto; margin-right: auto; max-width: 100%;\" />";
        //print "<p>".basename($file)."</p>";      
        //print "<hr style=\"border-bottom: 1px solid #E5E5E5;\">";
        print "</div>";
    }
?>
<!--</div>-->
</div>
</div>
</div>

<script src="js/ui.js"></script>


</body>
</html>
