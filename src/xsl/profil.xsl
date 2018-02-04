<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : profil.xsl
    Created on : 11 octobre 2017, 12:01
    Author     : diallo1
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:profil="http://myGame/tux">
    
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>profil du joueur</title>
            </head>
            <body>
                <h3>
                    Nom : <xsl:value-of select="//profil:name/text()"/>
                </h3>
                <h3>
                    Avatar : <xsl:apply-templates select="//profil:avatar"/>
                </h3>
                <h3>
                    Birthday : <xsl:value-of select="//profil:birthday/text()"/>
                </h3>
                <h3>
                    Games :
                </h3>
                <ul>
                     <xsl:apply-templates select="//profil:game"/>
                </ul>              
            </body>
        </html>
    </xsl:template>
    
    
    <!-- Template Avatar-->
    <xsl:template match="profil:avatar">
        <img src="./tux.jpeg"/>
    </xsl:template>
    
    <!-- Template game-->
    <xsl:template match="profil:game"> 
        <li>
            date :<xsl:value-of select="@date"/>
        </li>   
         <li>
            temps :<xsl:value-of select="//profil:time/text()"/>
        </li>
         <li>
            Mot :<xsl:value-of select="//profil:word/text()"/>
        </li>
        <li>
            Niveau :<xsl:value-of select="//profil:word/@niveau"/>
        </li>
        <li>
            Reussite :<xsl:value-of select="@found"/>
        </li>
    </xsl:template>
    

</xsl:stylesheet>
