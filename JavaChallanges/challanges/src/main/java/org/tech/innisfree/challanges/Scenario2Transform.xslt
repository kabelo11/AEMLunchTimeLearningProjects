<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text" omit-xml-declaration="yes"
		indent="no" encoding="utf-8" media-type="text/csv" />
<xsl:template match="Entries">
    <xsl:for-each select="Entry">
      @ProdDryRunKabelo
      Scenario: SPDR.02 Scenario 02.3 -  <xsl:value-of select="Fund"/> (Classification Money Market Fund)
      Given that "<xsl:value-of select="User"/>" is logged into the "External Interface"
      When she navigates to view the "Forms Portal - Ready Tab" page on the external interface
      #	When she navigates to view the "Forms Portal - In Progress Tab" page on the external interface
      Then she sees a form instance for a "C23" form for "<xsl:value-of select="Fund"/>" in the "2017/01" Release
      When she selects the "Start" action on the identified Form Instance
      #	 When she selects the "EDIT" action on the identified Form Instance
      And she uploads data from the "I0167916 MMF.xlsx" file into the form
      Then she sees upload "SUCCESSFUL" message "File uploaded successfully."
      When she acknowledges the "SUCCESSFUL" form upload message
      And she selects to submit the form, expecting it to "SUCCEED"
      And she navigates to view the "Forms Portal - Submitted Tab" page on the external interface
      Then she sees a form instance for a "C23" form for "<xsl:value-of select="Fund"/>" in the "2017/01" Release
    </xsl:for-each>  
</xsl:template>
</xsl:stylesheet>