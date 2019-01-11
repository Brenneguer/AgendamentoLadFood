package agendamento.dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import agendamento.Usuario;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UsuarioDao implements IDao<Usuario> {
	Connection conn = null;
	PreparedStatement query = null;
	ResultSet rs = null;
	String sql = "";

	@Override
	public boolean salvar(Usuario istance) {
		Usuario u = buscarUsuario(istance.getMail());
		if (u != null) {
			Alert alert = new Alert(AlertType.ERROR, "Ja temos esse usuário cadastrado.");
			alert.setHeaderText("Ooops.");
			alert.setTitle("ERROR");
			alert.show();
			return false;
		}
		if(istance.getSobrenome().equals("") || istance.getSenha().contentEquals("") || istance.getNome().equals("") || istance.getCpf().equals("") || istance.getMail().equals("")) {
			Alert alert = new Alert(AlertType.ERROR, "Insira informações validas.");
			alert.setHeaderText("Ooops.");
			alert.setTitle("ERROR");
			alert.show();
			return false;
		}
		conn = Connector.abrirConexao();
		sql = "INSERT INTO users (nome, sobrenome, mail, senha, cpf) VALUES (?,?,?,?,?)";
		boolean bool = false;

		try {
			query = conn.prepareStatement(sql);
			query.setString(1, istance.getNome());
			query.setString(2, istance.getSobrenome());
			query.setString(3, istance.getMail());
			query.setString(5, istance.getCpf());
			if (encriptografar(istance.getSenha()) != null) {
				query.setString(4, encriptografar(istance.getSenha()));
			}
			
			int insert = 0;
			insert = query.executeUpdate();
			System.out.println("insert");
			if (insert > 0) {
				Alert alert = new Alert(AlertType.INFORMATION, "Salvo com sucesso.");
				alert.setHeaderText("CONFIRMAÇÃO!");
				alert.setTitle("Sucesso.");
				JOptionPane.showMessageDialog(null, "salvado");
				bool = true;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			Connector.fecharConexao(conn, query, rs);
		}
		return bool;
	}

	/**
	 * buscar usuario somente por e-mail
	 * @param mail
	 * @return User
	 */
	public Usuario buscarUsuario(String mail) {
		Usuario u = null;
		sql = "SELECT * FROM users WHERE mail = ?";
		conn = Connector.abrirConexao();
		
		try {
			query = conn.prepareStatement(sql);
			query.setString(1, mail);
			rs = query.executeQuery();
			if(rs.next()) {
				u = new Usuario();
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setNome(rs.getString("nome"));
				u.setSobrenome(rs.getString("sobrenome"));
				u.setMail(rs.getString("mail"));
				u.setCpf(rs.getString("cpf"));
			}
		} catch (SQLException e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
		return u;
	}
	
	@Override
	public boolean deletar(int i) {
		return false;
	}
	
	/**
	 * Lista os usuarios no bd
	 */
	
	@Override
	
	public List<Usuario> listarTodos() {
		conn = Connector.abrirConexao();
		sql = "SELECT * FROM users";
		Usuario u = null;
		List<Usuario> list = new ArrayList<Usuario>();

		try {

			query = conn.prepareStatement(sql);
			rs = query.executeQuery();
			while (rs.next()) {
				u = new Usuario();
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setNome(rs.getString("nome"));
				u.setSobrenome(rs.getString("sobrenome"));
				u.setMail(rs.getString("mail"));
				u.setCpf(rs.getString("cpf"));
				list.add(u);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Connector.fecharConexao(conn, query, rs);
		}
		return list;
	}
	/**
	 * verifica se a senha é compativel
	 * @param mail
	 * @param senha
	 * @return boolean
	 */
	public boolean buscarUsuario(String mail, String senha) {
		conn = Connector.abrirConexao();
		sql = "SELECT senha FROM users WHERE mail = ?";
		boolean bool = false;
		String log;
		try {
			query = conn.prepareStatement(sql);
			query.setString(1, mail);
			rs = query.executeQuery();
			if (rs.next()) {
				log = rs.getString("senha");
				if(encriptografar(senha).equals(log)) {
					bool = true;
				}
			} 
		} catch (SQLException e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
		return bool;
	}

	private String encriptografar(String string) {
		String cript = null;
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(string.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
				cript = hexString.toString();
			}

		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e);
		}
		return cript;
	}

}
