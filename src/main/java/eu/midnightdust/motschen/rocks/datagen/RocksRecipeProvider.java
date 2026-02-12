package eu.midnightdust.motschen.rocks.datagen;

import eu.midnightdust.motschen.rocks.Rocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;

import java.util.concurrent.CompletableFuture;

public class RocksRecipeProvider extends RecipeProvider {
	private final RecipeOutput output;

	public RocksRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
		super(provider, recipeOutput);
		this.output = recipeOutput;
	}

	@Override
	protected void buildRecipes() {
		Rocks.splittersByType.forEach(((rockType, splitter) -> {
			shapeless(RecipeCategory.BUILDING_BLOCKS, BuiltInRegistries.BLOCK.getValue(Identifier.withDefaultNamespace(rockType.name().toLowerCase())).asItem())
					.requires(splitter, 4).unlockedBy(RecipeProvider.getHasName(splitter), this.has(splitter))
					.save(this.output, "rocks:" + rockType.name().toLowerCase() + "_from_splitter");
		}));
	}

	public static class Runner extends RecipeProvider.Runner {
		public Runner(PackOutput output, CompletableFuture<Provider> completableFuture) {
			super(output, completableFuture);
		}

		@Override
		protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
			return new RocksRecipeProvider(provider, recipeOutput);
		}

		@Override
		public String getName() {
			return "This Rocks Recipes";
		}
	}


}
