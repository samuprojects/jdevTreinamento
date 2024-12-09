package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificarCampoPesquisa;

@Audited
@Entity
@Table(name = "cidade")
@SequenceGenerator(name = "cidade_seq", sequenceName = "cidade_seq", initialValue = 1, allocationSize = 1)
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificarCampoPesquisa(descricaoCampo = "Código", campoConsulta = "cid_codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidade_seq")
	private Long cid_cod;
	
	@IdentificarCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "cid_descricao", principal = 1)
	@Column(length = 100, nullable = false)
	private String cid_descricao;
	
	@IdentificarCampoPesquisa(descricaoCampo = "Estado", campoConsulta = "estado.est_nome")
	@Basic
	@ManyToOne
	@JoinColumn(name = "estado", nullable = false)
	@ForeignKey(name = "estado_fk")
	private Estado estado = new Estado();
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getCid_cod() {
		return cid_cod;
	}

	public void setCid_cod(Long cid_cod) {
		this.cid_cod = cid_cod;
	}

	public String getCid_descricao() {
		return cid_descricao;
	}

	public void setCid_descricao(String cid_descricao) {
		this.cid_descricao = cid_descricao;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cid_cod);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		return Objects.equals(cid_cod, other.cid_cod);
	}

	@Override
	public String toString() {
		return "Cidade [cid_cod=" + cid_cod + ", cid_descricao=" + cid_descricao + "]";
	}
	
	

}
