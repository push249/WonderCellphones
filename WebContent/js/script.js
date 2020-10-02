/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function()
{
    $(":text").after("<span class=\"error\"></span>");
    $(":password").after("<span class=\"error\"></span>");
    $("#signin").submit(
            function(event) {
                var isValid = true;
                var appname = $("#username").val().trim();
                if (appname == "") {
                    $("#username").next().text("*This field is required.");
                    isValid = false;
                }
                else {
                    $("#username").val(appname);
                    $("#username").next().text("");
                }
                var password = $("input[name='password']").val();
                if (password == "") {
                    $("input[name='password']").next().text("*This field is required.");
                    isValid = false;
                }
                else {
                    $("input[name='password']").next().text("");
                }
                if (isValid == false) {
                    event.preventDefault();
                }
                if (isValid == true) {
                }
            }
    );
    $("#signup").submit(
            function(event) {
                var isValid = true;
                var appname = $("#firstname").val().trim();
                if (appname == "") {
                    $("#firstname").next().text("*This field is required.");
                    isValid = false;
                }
                else if (appname.length < 1 || appname.length > 25) {
                    $("#firstname").next().text("*This field is incorrect.");
                    $("#firstname").
                            isValid = false;
                }
                else {
                    $("#firstname").val(appname);
                    $("#firstname").next().text("");
                }
                appname = $("#lastname").val().trim();
                if (appname == "") {
                    $("#lastname").next().text("*This field is required.");
                    $("#lastname").
                            isValid = false;
                }
                else if (appname.length < 1 || appname.length > 25) {
                    $("#lastname").next().text("*This field is incorrect.");
                    $("#lastname").
                            isValid = false;
                }
                else {
                    $("#lastname").val(appname);
                    $("#lastname").next().text("");
                }
                appname = $("#username").val().trim();
                if (appname == "") {
                    $("#username").next().text("*This field is required.");
                    $("#username").
                            isValid = false;
                }
                else if (appname.length < 1 || appname.length > 25) {
                    $("#username").next().text("*This field is incorrect.");
                    $("#username").
                            isValid = false;
                }
                else {
                    $("#username").val(appname);
                    $("#username").next().text("");
                }
                appname = $("#address").val().trim();
                if (appname == "") {
                    $("#address").next().text("*This field is required.");
                    $("#address").
                            isValid = false;
                }
                else {
                    $("#address").val(appname);
                    $("#address").next().text("");
                }

                var emailPattern = /\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}\b/;
                var email = $("#emailid").val();
                if (email == "") {
                    $("#emailid").next().text("*This field is required.");
                    isValid = false;
                } else if (!emailPattern.test(email)) {
                    $("#emailid").next().text("*Must be a valid email address.");
                    isValid = false;
                } else {
                    $("#emailid").next().text("");
                }

                var phonePattern = /\([0-9]{3}\)+[0-9]{7}/;
                var telephonenumber = $("#contactno").val();
                if (telephonenumber == "") {
                    $("#contactno").next().text("*This field is required.");
                    isValid = false;
                } else if (!phonePattern.test(telephonenumber)) {
                    $("#contactno").next().text("*Must be a valid phone number in this format:(xxx)xxxxxxx");
                    isValid = false;
                } else {
                    $("#contactno").next().text("");
                }
                var password1 = $("input[name='password1']").val();
                var password2 = $("input[name='password2']").val();
                if (password1 == "" && password2 == "") {
                    $("input[name='password1']").next().text("*This field is required.");
                    $("input[name='password2']").next().text("*This field is required.");
                    isValid = false;
                } else if ((password1 != password2)) {
                    $("input[name='password2']").next().text("*Passwords do not match.");
                    isValid = false;
                } else {
                    $("#password1").next().text("");
                    $("#password2").next().text("");
                }
                if (isValid == false) {
                    event.preventDefault();
                }
                if (isValid == true) {
                }
            }
    );
    $("#updateinf").submit(
            function(event) {
                var isValid = true;
                var phonePattern = /\([0-9]{3}\)+[0-9]{7}/;
                var telephonenumber = $("#contactno").val();
                if (telephonenumber != "") {
                    if (!phonePattern.test(telephonenumber)) {
                        $("#contactno").next().text("*Must be a valid phone number.");
                        isValid = false;
                    }
                } else {
                    $("#contactno").next().text("");
                }
                var password = $("input[name='password']").val();
                var password1 = $("input[name='password1']").val();
                var password2 = $("input[name='password2']").val();
                if (!(password1 == "" && password2 == "" && password == "")) {
                    if (password1 == "" || password2 == "" || password == "") {
                        $("input[name='password']").next().text("*This field is required.");
                        $("input[name='password1']").next().text("*This field is required.");
                        $("input[name='password2']").next().text("*This field is required.");
                        isValid = false;
                    }
                    else if (password == password1) {
                        $("input[name='password2']").next().text("*New password is the same.");
                        isValid = false;
                    }
                    else if ((password1 != password2)) {
                        $("input[name='password2']").next().text("*Passwords do not match.");
                        isValid = false;
                    }
                }
                else {
                    $("#password").next().text("");
                    $("#password1").next().text("");
                    $("#password2").next().text("");
                }
                if (isValid == false) {
                    event.preventDefault();
                }
                if (isValid == true) {
                    //finalize();
                }
            }
    );
    $("#checkout").submit(
            function(event) {
                var isValid = true;
                var appname = $("#cardname").val().trim();
                if (appname == "") {
                    $("#cardname").next().text("*This field is required.");
                    isValid = false;
                }
                else if (appname.length < 1 || appname.length > 25) {
                    $("#cardname").next().text("*This field is incorrect.");
                    isValid = false;
                }
                else {
                    $("#cardname").val(appname);
                    $("#cardname").next().text("");
                }
                var cardPattern = /[0-9]{16}/;
                var cardnumber = $("#cardno").val();
                if ((!cardPattern.test(cardnumber)) || cardnumber.length != 16) {
                    $("#cardno").next().text("*Must be a valid credit/debit card number.");
                    isValid = false;
                }
                else {
                    $("#cardno").next().text("");
                }

                var fullDate = new Date();
                console.log(fullDate);
                var twoDigitMonth = (fullDate.getMonth() + 1) + "";
                if (twoDigitMonth.length == 1)
                    twoDigitMonth = "0" + twoDigitMonth;
                var fourDigitYear = fullDate.getFullYear();
                var expdate = $("#expdate").val();
                var expdatePattern = /[0-9]{2}\/[0-9]{4}/;
                if (expdate == "")
                {
                    $("#expdate").next().text("*This field is required.");
                    isValid = false;
                }
                else if (!expdatePattern.test(expdate)) {
                    $("#expdate").next().text("*Expiry date invalid.");
                    isValid = false;
                }
                else if (expdatePattern.test(expdate)) {
                    var month = parseInt(expdate[0] + expdate[1]);
                    var year = parseInt(expdate[3] + expdate[4] + expdate[5] + expdate[6]);
                    if (parseInt(year, 10) <= parseInt(fourDigitYear, 10))
                    {
                        if (parseInt(month, 10) < parseInt(twoDigitMonth, 10)) {
                            $("#expdate").next().text("*Credit Card Expired.");
                            isValid = false;
                        }
                        else {
                            $("#expdate").next().text("");
                        }
                    }
                }
                else {
                    $("#expdate").next().text("");
                }
                var cvvPattern = /[0-9]{3}/;
                var cvv = $("input[name='cvv']").val();
                if (!cvvPattern.test(cvv) || cvv.length != 3) {
                    $("#cvv").next().text("*Invalid cvv number.");
                    isValid = false;
                }
                else {
                    $("#cvv").next().text("");
                }
                if (isValid == false) {
                    event.preventDefault();
                }
                if (isValid == true) {
                    //finalize();
                }

            }

    );
}); 