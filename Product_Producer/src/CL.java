
	import java.io.File;
import java.lang.reflect.Method;     
import java.net.URL;     
import java.net.URLClassLoader;     
import java.util.ArrayList;     
import java.util.List;     
	    
	public final class CL {     
	    /** URLClassLoader的addURL方法 */    
	    /*private static Method addURL = initAddMethod();     */
	    
	    public static void main(String arg[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
//	    	loadJarFile(new File("C:\\Users\\Administrator\\workspace\\F.jar"));
//	    	loadJarFile(new File("C:\\Users\\Administrator\\workspace\\f\\F.jar"));
//	    	Class c=Class.forName("F");
//	    	c.newInstance();
//	    	
//	    	long l=System.currentTimeMillis();
//	    	for(int a=0;a<100;a++){
//	    		String f = new String();}
//	    	System.out.println(System.currentTimeMillis() - l);
//	    	l=System.currentTimeMillis();
//	    	for(int a=0;a<100;a++){
//	    		c=Class.forName("java.lang.String"); c.newInstance();}
//	    	System.out.println(System.currentTimeMillis() - l);
	    	
	    	String s = new String("1");  
	        s.intern();  
	        String s2 = "1";  
	        System.out.println(s == s2);  
	  
	        String s3 = new String("12");  
	        s3.intern();  
	        String s4 = "12";  
	        System.out.println(s3 == s4);  
	          
	        String s5 = new String("1") + new String("1");  
	        s5.intern();  
	        String s6 = "11";  
	        System.out.println(s5 == s6);  //s3=11 false
	        
	        String s7 = new String(new char[]{'a' , 'b'});  
//	        s5.intern();  
	        String s8 = "ab";  
	        System.out.println(s7 ==s8);  
	        
	        String str1 = "str01";
	        String str2 = new String("str")+new String("01");
	        /*str2=*/str2.intern();
	        System.out.println(str1 ==str2); 
	    }
	    /** 初始化方法 */    
	   /* private static final Method initAddMethod() {     
	        try {     
	            Method add = URLClassLoader.class    
	                .getDeclaredMethod("addURL", new Class[] { URL.class });     
	            add.setAccessible(true);     
	            return add;     
	        } catch (Exception e) {     
	            e.printStackTrace();     
	        }     
	        return null;     
	    }     
	    
	    private static URLClassLoader system = (URLClassLoader) ClassLoader.getSystemClassLoader();     
	    
	    *//**   
	     * 循环遍历目录，找出所有的JAR包   
	     *//*    
	    private static final void loopFiles(File file, List<File> files) {     
	        if (file.isDirectory()) {     
	            File[] tmps = file.listFiles();     
	            for (File tmp : tmps) {     
	                loopFiles(tmp, files);     
	            }     
	        } else {     
	            if (file.getAbsolutePath().endsWith(".jar") || file.getAbsolutePath().endsWith(".zip")) {     
	                files.add(file);     
	            }     
	        }     
	    }     
	    
	    *//**   
	     * <pre>   
	     * 加载JAR文件   
	     * </pre>   
	     *   
	     * @param file   
	     *//*    
	    public static final void loadJarFile(File file) {     
	        try {     
	            addURL.invoke(system, new Object[] { file.toURI().toURL() });     
	            System.out.println("加载JAR包：" + file.getAbsolutePath());     
	        } catch (Exception e) {     
	            e.printStackTrace();     
	        }     
	    }     
	    
	    *//**   
	     * <pre>   
	     * 从一个目录加载所有JAR文件   
	     * </pre>   
	     *   
	     * @param path   
	     *//*    
	    public static final void loadJarPath(String path) {     
	        List<File> files = new ArrayList<File>();     
	        File lib = new File(path);     
	        loopFiles(lib, files);     
	        for (File file : files) {     
	            loadJarFile(file);     
	        }     
	    }     */
	}    
