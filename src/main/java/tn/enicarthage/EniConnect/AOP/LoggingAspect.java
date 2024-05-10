package tn.enicarthage.EniConnect.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

    @Aspect
    @Component
    public class LoggingAspect {

        private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

        @AfterReturning(pointcut = "execution(* tn.enicarthage.EniConnect.controllers.AncienEtudiantController.addAncienEtudiant(..))", returning = "result")
        public void logAddAncienEtudiant(JoinPoint joinPoint, Object result) {
            logger.info("Méthode addAncienEtudiant appelée avec les arguments : {}", joinPoint.getArgs());
            logger.info("Résultat de l'appel à addAncienEtudiant : {}", result);
        }
    }

