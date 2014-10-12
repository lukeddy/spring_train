<h2 align="center">Spring Form Tags</h2>
<form:form method="POST" name="myForm" commandName="myForm" action="#">
    <table align="center" cellpadding="10" cellspacing="10"
           bgcolor="#CCFFCC">
        <tr>
            <td><form:label path="hiddenField">Hidden Field</form:label></td>
            <td><form:hidden path="hiddenField"></form:hidden></td>
        </tr>
        <tr>
            <td><form:label path="textField">Text Field</form:label></td>
            <td><form:input path="textField"></form:input></td>
        </tr>
        <tr>
            <td><form:label path="password">Password</form:label></td>
            <td><form:input path="password"></form:input></td>
        </tr>
        <tr>
            <td><form:label path="radioButton">Radio Button</form:label></td>
            <td><form:radiobutton path="radioButton" value="M" label="M"></form:radiobutton>
                <form:radiobutton path="radioButton" value="F" label="F"></form:radiobutton></td>
        </tr>
        <tr>
            <td><form:label path="radioButton">Radio Buttons from List</form:label></td>
            <td><form:radiobuttons items="${countryList}" path="checkBox"
                                   itemLabel="name" itemValue="id"></form:radiobuttons></td>
        </tr>
        <tr>
            <td><form:label path="checkBox">Check Box</form:label></td>
            <td><form:checkbox path="checkBox" value="S" label="Student"></form:checkbox>
                <form:checkbox path="checkBox" value="E" label="Employee"></form:checkbox></td>
        </tr>
        <tr>
            <td><form:label path="checkBox">Check Boxes from List</form:label></td>
            <td><form:checkboxes items="${countryList}" path="checkBox"
                                 itemLabel="name" itemValue="id" /></td>
        </tr>
        <tr>
            <td><form:label path="select">Select Country</form:label></td>
            <td><form:select path="select">
                <form:option value="0" label="Please Select"></form:option>
                <form:options items="${countryList}" itemValue="id" itemLabel="name" />
            </form:select></td>
        </tr>

        <tr>
            <td><form:label path="textArea">Text Area</form:label></td>
            <td><form:textarea path="textArea" cols="35" rows="3"></form:textarea></td>
        </tr>

        <tr>
            <td><form:label path="file">File Upload</form:label></td>
            <td><form:input type="file" path="file"></form:input></td>
        </tr>

        <tr>
            <td align="right"><input type="submit" value="Submit Form"></input></td>
            <td align="center"><input type="reset" value="Clear Form"></input></td>
        </tr>
    </table>
</form:form>