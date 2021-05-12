package ThongTin;

public class DaiLy {

	private String tenDaiLy;
	private String diaChi;
	private String soDienThoai;
	private String vungMien;
	private String tenDangNhap;
	private String matKhau;

	public DaiLy(String tenDaiLy, String diaChi, String soDienThoai, String vungMien, String tenDangNhap, String matKhau) {
		this.tenDaiLy = tenDaiLy;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.vungMien = vungMien;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
	}
	
	public DaiLy() {
	}
	
	public String getTenDaiLy() {
		return tenDaiLy;
	}
	
	public void setTenDaiLy(String tenDaiLy) {
		this.tenDaiLy = tenDaiLy;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getVungMien() {
		return vungMien;
	}

	public void setVungMien(String vungMien) {
		this.vungMien = vungMien;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
}
