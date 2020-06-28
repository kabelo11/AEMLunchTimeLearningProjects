<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text" omit-xml-declaration="yes"
		indent="no" encoding="utf-8" media-type="text/csv" />
<xsl:template match="Entries">
    <xsl:for-each select="Entry">
@ProdDryRun
@ProdDryRunKabelo
Scenario: SPDR.03 Scenario 04 - <xsl:value-of select="Fund"/>
Given that "<xsl:value-of select="User"/>" is logged into the "External Interface"
When he navigates to view the "Forms Portal - Ready Tab" page on the external interface
# When he navigates to view the "Forms Portal - In Progress Tab" page on the external interface
Then he sees a form instance for a "C23" form for "<xsl:value-of select="Fund"/>" in the "2017/01" Release
When he selects the "Start" action on the identified Form Instance
# When she selects the "EDIT" action on the identified Form Instance
And he captures "<xsl:value-of select="Fund"/>" data into the form
And he selects to save the form
And he selects to submit the form, expecting it to "SUCCEED"
And he navigates to view the "Forms Portal - Submitted Tab" page on the external interface
Then he sees a form instance for a "C23" form for "<xsl:value-of select="Fund"/>" in the "2017/01" Release
    </xsl:for-each>  
</xsl:template>
</xsl:stylesheet>