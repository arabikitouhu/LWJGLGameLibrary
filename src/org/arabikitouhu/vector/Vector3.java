package org.arabikitouhu.vector;


public class Vector3 {

	/** 足し算(return src1 + src2) */
	public static Vector3 Addition(Vector3 src1, Vector3 src2) {
		return new Vector3(src1.GetX() + src2.GetX(), src1.GetY() + src2.GetY(), src1.GetZ() + src1.GetZ());
	}

	/** 引き算(return src1 - src2) */
	public static Vector3 Subtract(Vector3 src1, Vector3 src2) {
		return new Vector3(src1.GetX() - src2.GetX(), src1.GetY() - src2.GetY(), src1.GetZ() - src1.GetZ());
	}

	/** 掛け算(return src1・src2) */
	public static Vector3 Multiply(Vector3 src1, Vector3 src2) {
		return new Vector3(src1.GetX() * src2.GetX(), src1.GetY() * src2.GetY(), src1.GetZ() * src1.GetZ());
	}



	protected float[] vecs;

	public Vector3(float x, float y, float z) {
		vecs = new float[] { x, y, z };
	}

	public Vector3(float[] value) {
		vecs = value;
	}

	public Vector3(Vector3 value) {
		vecs[0] = value.vecs[0];
		vecs[1] = value.vecs[1];
		vecs[2] = value.vecs[2];
	}

	public Vector3(double x, double y, double z) {
		vecs = new float[] { (float)x, (float)y, (float)z };
	}

	public float GetX() { return vecs[0]; }

	public float GetY() { return vecs[1]; }

	public float GetZ() { return vecs[2]; }

	/** Vec3→Vec4変換(return Vec4(x, y, z, 1)) */
	public Vector4 GetVector4() { return new Vector4(vecs[0], vecs[1], vecs[2], 1f); }

	/** 各要素を足した値(W) */
	public float GetW() { return vecs[0] + vecs[1] + vecs[2]; }

	/** 複製 */
	public Vector3 ToCopy() { return new Vector3(this); }

	/** 正規化の取得(各要素の合計が1になるベクトル) */
	public Vector3 Normalize() {
		float w = vecs[0] + vecs[1] + vecs[2];
		return new Vector3(vecs[0] / w, vecs[1] / w, vecs[2] / w);
	}

	/** 反ベクトルの取得 */
	public Vector3 Inverse() { return new Vector3(-GetX(), -GetY(), -GetZ()); }

	/** 先端から終端へX軸方向に指定した値分移動した場合のベクトル
	 * @param end 終端
	 * @param value 移動量
	 */
	public Vector3 IntermediateWithXValue(Vector3 end, float value) {
		double dx = end.GetX() - this.GetX();
		double dy = end.GetY() - this.GetY();
		double dz = end.GetZ() - this.GetZ();

		if (dx * dx < 1.0000000116860974E-7D) {
			return null;
		} else {
			double dt = (value - this.GetX()) / dx;	//移動量をパーセンテージにした値(100%)
			// 0～100%を超えたら解なし
			return dt >= 0.0D && dt <= 1.0D ?
				new Vector3(this.GetX() + dx * dt, this.GetY() + dy * dt, this.GetZ() + dz * dt) : null;
		}
	}

	/** 先端から終端へY軸方向に指定した値分移動した場合のベクトル
	 * @param end 終端
	 * @param value 移動量
	 */
	public Vector3 IntermediateWithYValue(Vector3 end, float value) {
		double dx = end.GetX() - this.GetX();
		double dy = end.GetY() - this.GetY();
		double dz = end.GetZ() - this.GetZ();

		if (dy * dy < 1.0000000116860974E-7D) {
			return null;
		} else {
			double dt = (value - this.GetX()) / dx;	//移動量をパーセンテージにした値(100%)
			// 0～100%を超えたら解なし
			return dt >= 0.0D && dt <= 1.0D ?
				new Vector3(this.GetX() + dx * dt, this.GetY() + dy * dt, this.GetZ() + dz * dt) : null;
		}
	}

	/** 先端から終端へZ軸方向に指定した値分移動した場合のベクトル
	 * @param end 終端
	 * @param value 移動量
	 */
	public Vector3 IntermediateWithZValue(Vector3 end, float value) {
		double dx = end.GetX() - this.GetX();
		double dy = end.GetY() - this.GetY();
		double dz = end.GetZ() - this.GetZ();

		if (dz * dz < 1.0000000116860974E-7D) {
			return null;
		} else {
			double dt = (value - this.GetX()) / dx;	//移動量をパーセンテージにした値(100%)
			// 0～100%を超えたら解なし
			return dt >= 0.0D && dt <= 1.0D ?
				new Vector3(this.GetX() + dx * dt, this.GetY() + dy * dt, this.GetZ() + dz * dt) : null;
		}
	}


	/** 2点間の距離の取得(ピタゴラスの定理) */
	public float DistanceTo(Vector3 value) { return (float)Math.sqrt(SquaredDistanceTo(value)); }

	/** 2点間の距離の二乗値の取得 */
	public double SquaredDistanceTo(Vector3 value) {
		double dx = value.GetX() - this.GetX();
		double dy = value.GetY() - this.GetY();
		double dz = value.GetZ() - this.GetZ();
		return dx * dx + dy * dy + dz * dz;
	}

}
