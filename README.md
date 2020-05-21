# Header Modifier
Header Modifier is a Burp extension to allow the users to modify, add or remove any request header. The tool works with HTTP requests sent using Burp Scanner and Burp Extender.
### Installation
* Download the latest jar file from the “Build” directory.
* Follow the functional flow:
    * Burp Extender > Extensions > Add.
* Specify the location of the downloaded Jar file.
* Click “Next”.
* Use the “Errors” tab to verify no errors were generated.

### Usage
<ul>Add Header
    <ul>
      <li>Header Name: Authorization</li>
      <li>Header Value: Bearer 12345678</li>
      <li>Header added to the request &rArr; Authorization: Bearer 12345678</li>
    </ul> 
</ul>
<ul>Remove Header
    <ul>
      <li>Header Name : Authorization</li>
    </ul> 
</ul>
<ul>Modify Header
    <ul>
      <li>Header Name : Authorization</li>
      <li>Header Value: Bearer 12345678</li>
      <li>Modified header added to the request &rArr; Authorization: Bearer 12345678</li>
    </ul> 
</ul>

### Changelog
<ul> v1.2
    <ul>
      <li>Added JScrollPane for header name and value</li>
    </ul> 
</ul> 
<ul> v1.1
    <ul>
      <li>Java SDK version: 13.0.2</li>
      <li>Added option for Burp tool selection</li>
      <li>Minor UI changes</li>
    </ul> 
</ul> 

### Reporting
Please report any bugs or any features that you recommend should be added to the extension.
