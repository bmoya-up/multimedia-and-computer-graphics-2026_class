//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //String[] command = new String[]{"curl", "-X", "POST", "https://postman-echo.com/post", "--data", "name=Moya"};
    //String[] command = new String[]{"cmd.exe", "/c", "dir"};
    String[] command = new String[]{"whoami"};

    final ProcessBuilder builder = new ProcessBuilder();
    try{
        final Process process = builder.command(command).start();
        InputStream inputStream = process.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        process.destroy();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
