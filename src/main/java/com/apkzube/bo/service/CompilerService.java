package com.apkzube.bo.service;

import com.apkzube.bo.service.dto.CompilerFormDTO;
import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompilerService {

    private Logger log = LoggerFactory.getLogger(CompilerService.class);

    public CompilerFormDTO runPythonCode(CompilerFormDTO compilerFormDTO) {
        try {
            String prg = compilerFormDTO.getCode();
            File file = new File("test1.py");
            FileWriter out = new FileWriter(file);
            out.write(prg);
            out.close();

            String input = "";
            if (compilerFormDTO.getInput() != null) {
                for (String in : compilerFormDTO.getInput().split(",")) {
                    input = input + in.trim() + " ";
                }
            }

            String cmdCode = "python test1.py " + file.getAbsolutePath() + " " + input;

            /* ProcessBuilder processBuilder=new ProcessBuilder("python", file.getAbsolutePath() + " "+input).inheritIO();

            Process p = processBuilder.start();
            p.waitFor();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8));

            // BufferedReader bfr = new BufferedReader(new InputStreamReader());
            String line = "";


            while ((line = bfr.readLine()) != null) {
                line=line+"\n";
            }*/

            Process p = Runtime.getRuntime().exec(cmdCode);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            StringBuilder output = new StringBuilder();

            while ((line = bfr.readLine()) != null) {
                output.append(line).append("\n");
            }

            BufferedReader errbfr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            StringBuilder error = new StringBuilder();
            line = "";
            while ((line = bfr.readLine()) != null) {
                error.append(line).append("\n");
            }

            compilerFormDTO.setOutput(output.toString());
            compilerFormDTO.setError(error.toString());
        } catch (Exception e) {
            log.error("Error in running python code :: " + e.getMessage());
            compilerFormDTO.setError(e.getMessage());
        }

        return compilerFormDTO;
    }
}
