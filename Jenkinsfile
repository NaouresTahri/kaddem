pipeline { 
   agent any  
   stages { 
       stage('Checkout code') { 
           steps { 
               checkout scm  
           } 
       } 
       stage('Display system date') { 
           steps { 
               sh 'date' 
           } 
       } 
   } 
} 
