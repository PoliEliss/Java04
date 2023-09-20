package ru.t1.eliseeva.tm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class ManifestReader {
    public static void main(String[] args) throws IOException {
        Enumeration<URL> manifests = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
        URL manifest = null;
        while(manifests.hasMoreElements())
        {
            URL tmp = manifests.nextElement();
            System.out.println(tmp.getFile());
            if(tmp.getFile().contains("test.jar") || tmp.getFile().contains("/target/"))
            {
                manifest = tmp;
                break;
            }
        }
        if(manifest == null)
        {
            System.out.println("No manifest.");
            return;
        }
        System.out.println(manifest);
        try(InputStream is = manifest.openStream())
        {
            Manifest man = new Manifest(is);
            for(Map.Entry<Object, Object> kv : man.getMainAttributes().entrySet())
            {
                System.out.println(kv.getKey()+" = "+kv.getValue());
            }
            System.out.println("Version: "+man.getMainAttributes().getValue("Version"));
        }
    }
}
