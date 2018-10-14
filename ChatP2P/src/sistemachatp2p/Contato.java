package sistemachatp2p;

public class Contato {
	private String nickname;
	private String IP;
	private String porta;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getPorta() {
		return porta;
	}
	public void setPorta(String porta) {
		this.porta = porta;
	}
	public Contato(String nickname, String iP, String porta) {
		super();
		this.nickname = nickname;
		IP = iP;
		this.porta = porta;
	}
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Contato other = (Contato) obj;
	    if (nickname == null) {
	        if (other.nickname != null)
	            return false;
	    } else if (!nickname.equals(other.nickname))
	        return false;
	    return true;
	}
	

}
