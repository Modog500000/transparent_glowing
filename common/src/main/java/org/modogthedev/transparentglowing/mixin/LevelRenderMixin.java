package org.modogthedev.transparentglowing.mixin;
import org.modogthedev.transparentglowing.Constants;
import org.modogthedev.transparentglowing.TransparentGlowing;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({LevelRenderer.class})
public class LevelRenderMixin {
    public LevelRenderMixin() {
    }

    @Redirect(
            method = {"doEntityOutline"},
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;blendFuncSeparate(Lcom/mojang/blaze3d/platform/GlStateManager$SourceFactor;Lcom/mojang/blaze3d/platform/GlStateManager$DestFactor;Lcom/mojang/blaze3d/platform/GlStateManager$SourceFactor;Lcom/mojang/blaze3d/platform/GlStateManager$DestFactor;)V"
            )
    )
    private void doEntityOutline(GlStateManager.SourceFactor sourceFactor, GlStateManager.DestFactor destFactor, GlStateManager.SourceFactor sourceFactor2, GlStateManager.DestFactor destFactor2) {
        if (Constants.enabled) {
            RenderSystem.blendFuncSeparate(SourceFactor.SRC_COLOR, DestFactor.ONE_MINUS_SRC_COLOR, SourceFactor.ZERO, DestFactor.ONE);
        } else {
            RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ZERO, DestFactor.ONE);
        }

    }
}
