<?php
	$path_htdocs = '/Applications/XAMPP/xamppfiles/htdocs';

	$query1submitted = FALSE;
	$query2submitted = FALSE;

	$queryresult = '';

	if ($_SERVER["REQUEST_METHOD"] == "POST") {
		$queryresult = '';

		if(isset($_POST['submit1'])) {
			$query1submitted = TRUE;
			$queryresult = shell_exec('export DYLD_LIBRARY_PATH=\"\"; /Applications/XAMPP/xamppfiles/htdocs/paxquery/paxquery-installation/scripts/paxquery-run.sh /Applications/XAMPP/xamppfiles/htdocs/paxquery/io/query1.txt /Applications/XAMPP/xamppfiles/htdocs/paxquery/io/xoutput.xml 1 drawtrees');
		}
		else if(isset($_POST['submit2'])) {
			$query2submitted = TRUE;
			//$queryresult = 'Query 2 results';
			//$queryresult = shell_exec('export DYLD_LIBRARY_PATH=\"\"; /usr/local/bin/dot -Tpng /Applications/XAMPP/xamppfiles/htdocs/paxquery/paxquery-installation/web/images/plans/tp0.dot -o /Applications/XAMPP/xamppfiles/htdocs/paxquery/paxquery-installation/web/images/plans/test.png');
			$queryresult = shell_exec('export DYLD_LIBRARY_PATH=\"\"; /Applications/XAMPP/xamppfiles/htdocs/paxquery/paxquery-installation/scripts/paxquery-run.sh /Applications/XAMPP/xamppfiles/htdocs/paxquery/io/query2.txt /Applications/XAMPP/xamppfiles/htdocs/paxquery/io/xoutput.xml 1 drawtrees');
		}
		/* replace ampersands */
		shell_exec('sed -f /Applications/XAMPP/xamppfiles/htdocs/paxquery/utils/sed-replace-amp.sed /Applications/XAMPP/xamppfiles/htdocs/paxquery/io/xoutput.xml > /Applications/XAMPP/xamppfiles/htdocs/paxquery/io/xoutput2.xml');
		/* wrap result with <output></output> */
		shell_exec('echo \'<output>\' > /Applications/XAMPP/xamppfiles/htdocs/paxquery/io/final.xml');
		shell_exec('less /Applications/XAMPP/xamppfiles/htdocs/paxquery/io/xoutput2.xml >> /Applications/XAMPP/xamppfiles/htdocs/paxquery/io/final.xml');
		shell_exec('echo \'</output>\' >> /Applications/XAMPP/xamppfiles/htdocs/paxquery/io/final.xml');

		$_POST=array(); //clear _POST to restart state
	}
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Contact Input Form</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
	<!-- CSS by Pure http://purecss.io/ -->
    <link href="css/base-min.css" rel="stylesheet" type="text/css" hreflang="es" />
    <link href="css/forms.css" rel="stylesheet" type="text/css" hreflang="es" />
	<link href="css/buttons.css" rel="stylesheet" type="text/css" hreflang="es" />
</head>

<body>

<form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post" class="pure-form pure-form-aligned">
<fieldset>
<legend>Contact Input Form</legend>
<!--
<div class="pure-control-group">
	<label for="firstname">First name</label> 
	<input type="text" name="firstname" value="<?php echo isset($_POST['firstname']) ? $_POST['firstname'] : ''; ?>"/>
	<?php
		if($firstname_error)
			print "<label name=\"label_firstname_error\">Please enter</label>";
	?>
</div>
	
<div class="pure-control-group">
	<label for="lastname">Surname</label> 
	<input type="text" name="lastname" value="<?php echo isset($_POST['lastname']) ? $_POST['lastname'] : ''; ?>"/>
	<?php
		if($lastname_error)
			print "<label name=\"label_lastname_error\">Please enter</label>";
	?>
</div>

<div class="pure-control-group">
	<label for="address">Address</label> 
	<input type="text" name="address" value="<?php echo isset($_POST['address']) ? $_POST['address'] : ''; ?>"/>
</div>

<div class="pure-control-group">
	<label for="email">Email</label> 
	<input type="text" name="email" value="<?php echo isset($_POST['email']) ? $_POST['email'] : ''; ?>"/>
	<?php
		if($email_error)
		print "<label name=\"label_email_error\">Please enter</label>";
	?>
</div>

<div class="pure-control-group">
	<label for="phone">Telephone number</label> 
	<input type="text" name="phone" value="<?php echo isset($_POST['phone']) ? $_POST['phone'] : ''; ?>"/>
</div>
-->
<div class="pure-controls">
	<input id="submit" name="submit1" type="submit" value="Query 1" class="pure-button pure-button-primary">
	<label><?php if($query1submitted) print "Query 1 launched"?></label>
</div>
<div class="pure-controls">
	<input id="submit" name="submit2" type="submit" value="Query 2" class="pure-button pure-button-primary">
	<label><?php if($query2submitted) print "Query 2 launched"?></label>
</div>

<!--
<div class="pure-controls">
<label><?php print $queryresult?></label>
</div>
-->
<div class="pure-controls">
<textarea readonly="true"><?php print $queryresult?></textarea>
</div>


</fieldset>

</body>
</html>