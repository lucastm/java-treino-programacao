package br.com.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaminhoArquivo {


    private Path diretorio;

    private Path arquivo;

    private CaminhoArquivo(Path diretorio, Path arquivo) {
        super();
        this.diretorio = diretorio;
        this.arquivo = arquivo;
    }

    public Path getDiretorio() {
        return diretorio;
    }

    public Path getArquivo() {
        return arquivo;
    }

    public static CaminhoArquivo getInstance(Integer id) {
        String basePath = "/tmp/";
        Integer pageNumber = ((id - 1) / 1000) + 1;
        String dirName = basePath + pageNumber;
        return new CaminhoArquivo(Paths.get(dirName), Paths.get(dirName + "/" + id));

    }

}
