package org.sopt.daangn.domain.product.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.sopt.daangn.global.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class Product extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", columnDefinition = "TEXT", nullable = false)
	private String content;

	@Column(name = "price", nullable = false)
	private int price;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "last_bumped_at", nullable = false)
	private LocalDateTime lastBumpedAt;

	@Column(name = "trade_location", nullable = false)
	private String tradeLocation;

	@Column(name = "distance", nullable = false)
	private int distance;

	@Column(name = "view_count", nullable = false)
	private int viewCount;

	@Column(name = "like_count", nullable = false)
	private int likeCount;

	@Column(name = "thumb_nail_url", nullable = false)
	private String thumbnailUrl;

	@Column(name = "is_liked", nullable = false)
	private boolean isLiked;

	@Column(name = "seller_name", nullable = false)
	private String sellerName;

	@Column(name = "manner_temperature", nullable = false)
	private float mannerTemperature;

	@OneToMany(mappedBy = "product")
	private List<ProductImage> productImages = new ArrayList<>();
}
