
import br.com.goteam.teammate.dao.AdicionaDAO;
import br.com.goteam.teammate.modelo.Usuario;

public class main {

    public static void main(String[] args) {

        Usuario usuario = new Usuario("Rafael","teste");
        
        new AdicionaDAO<Usuario>().adiciona(usuario);
    }

}
